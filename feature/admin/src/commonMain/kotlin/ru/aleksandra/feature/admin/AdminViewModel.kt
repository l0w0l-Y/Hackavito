package ru.aleksandra.feature.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.deprecated.openFileSaver
import io.github.vinceglb.filekit.dialogs.openFilePicker
import io.github.vinceglb.filekit.readString
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.internal.GeneratedSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.serializer
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCase
import ru.aleksandra.core.sdui.presentation.mapper.toUi
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.UIEffect
import ru.aleksandra.core.sdui.presentation.model.UIState
import ru.aleksandra.feature.admin.model.Documentation

class AdminViewModel(
    private val adminApi: AdminApi,
) : ViewModel() {
    private var _ui = MutableStateFlow<UIState>(UIState.Init)
    val ui: StateFlow<UIState> = _ui

    private val _sideEffects: MutableSharedFlow<UIEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<UIEffect> = _sideEffects

    private var _uiElements = MutableStateFlow<List<Documentation>>(emptyList())
    val uiElements: StateFlow<List<Documentation>> = _uiElements

    private var _effect = MutableSharedFlow<AdminUIEffect>()
    val effect: SharedFlow<AdminUIEffect> = _effect

    init {
        getAllUIJson()
    }

    @OptIn(InternalSerializationApi::class)
    fun addJson(index: Int) {
        viewModelScope.launch {
            SDUIComponentDomain.subclasses[index].let { clazz ->
                val serializer = clazz.serializer() as KSerializer<SDUIComponentDomain>
                val jsonModel = toJsonModel(serializer, null)
                _effect.emit(AdminUIEffect.UpdateJson(prettyPrint(jsonModel)))
            }
        }
    }

    fun prettyPrint(jsonObject: JsonObject): String {
        val json = Json { prettyPrint = true }
        return json.encodeToString(JsonObject.serializer(), jsonObject).let {
            it/*.lines()//.filter { line -> !line.contains("\"__comment__\"") }
                .joinToString("\n")*/
                .replace(Regex("\"__comment__\":\\s*\"(.*?)\",?")) { matchResult ->
                    "// ${matchResult.groupValues[1]}"
                }
        }
    }

    @OptIn(InternalSerializationApi::class)
    fun getAllUIJson() {
        viewModelScope.launch {
            _uiElements.value =
                SDUIComponentDomain.subclasses.map {
                    val serializer = it.serializer() as KSerializer<SDUIComponentDomain>
                    val jsonModel = toJsonModel(serializer, null)
                    Documentation(
                        serializer.descriptor.serialName,
                        prettyPrint(jsonModel),
                        //prettyPrint(jsonModel)
                    )
                }
        }
    }

    @OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
    fun <T> toJsonModel(
        serializer: KSerializer<T>,
        value: T? = null
    ): JsonObject {
        val json = Json { encodeDefaults = false }
        val baseJson = if (value != null) {
            val elem = json.encodeToJsonElement(serializer, value)
            elem as? JsonObject ?: buildJsonObject { }
        } else {
            buildJsonObject { }
        }

        val descriptor = serializer.descriptor
        val generated = serializer as? GeneratedSerializer<*>
        val children: Array<KSerializer<*>>? = generated?.childSerializers()

        return buildJsonObject {
            put("type", JsonPrimitive(descriptor.serialName))

            for ((k, v) in baseJson) put(k, v)

            for (i in 0 until descriptor.elementsCount) {
                val name = descriptor.getElementName(i)
                if (baseJson.containsKey(name)) continue
                if (descriptor.isElementOptional(i)) continue

                val childSerializer = children?.getOrNull(i) as KSerializer<Any?>?
                val childDesc = childSerializer?.descriptor ?: descriptor.getElementDescriptor(i)
                val kind = childDesc.kind

                val defaultValue: JsonElement = when (kind) {
                    PrimitiveKind.STRING -> JsonPrimitive("")
                    PrimitiveKind.INT -> JsonPrimitive(0)
                    PrimitiveKind.LONG -> JsonPrimitive(0L)
                    PrimitiveKind.BOOLEAN -> JsonPrimitive(false)
                    PrimitiveKind.FLOAT -> JsonPrimitive(0f)
                    PrimitiveKind.DOUBLE -> JsonPrimitive(0.0)

                    StructureKind.LIST -> JsonArray(emptyList())
                    StructureKind.MAP -> JsonObject(emptyMap())

                    StructureKind.CLASS, StructureKind.OBJECT -> {
                        if (childSerializer != null) toJsonModel(childSerializer)
                        else JsonObject(emptyMap())
                    }

                    SerialKind.ENUM -> {
                        val first =
                            if (childDesc.elementsCount > 0) childDesc.getElementName(0) else ""
                        JsonPrimitive(first)
                    }

                    is PolymorphicKind -> {
                        // Генерируем комментарий с type + generic
                        val typeName = childDesc.serialName
                        val genericInfo = if (childDesc.elementsCount > 0) {
                            childDesc.getElementDescriptor(0).serialName
                        } else ""
                        val value =
                            "__comment__" to JsonPrimitive("type: $typeName${if (genericInfo.isNotEmpty()) "<$genericInfo>" else ""}")
                        JsonObject(mapOf(value))
                    }

                    else -> JsonNull
                }

                put(name, defaultValue)
            }
        }
    }


    fun loadFromJson(newJson: String) {
        viewModelScope.launch {
            _ui.value = UIState.Loading
            val json = Json {
                classDiscriminator = "type"
                prettyPrint = true
            }
            runCatching { json.decodeFromString<SDUIComponentDomain>(removeComments(newJson)) }
                .onSuccess { _ui.value = UIState.Loaded(it.toUi(JsonObject(emptyMap()))) }
                .onFailure { _ui.value = UIState.Error(it.message ?: "Неизвестная ошибка") }
        }
    }

    fun removeComments(code: String): String {
        val noBlockComments = code.replace(Regex("/\\*.*?\\*/", RegexOption.MULTILINE), "")
        val noLineComments = noBlockComments.replace(Regex("//.*"), "")
        return noLineComments
    }

    fun saveToFile(name: String, json: String) {
        viewModelScope.launch {
            FileKit.openFileSaver(
                json.encodeToByteArray(),
                name,
                "json"
            )
        }
    }

    fun openFile() {
        viewModelScope.launch {
            val result: PlatformFile? = FileKit.openFilePicker()
            result?.let {
                val content = it.readString()
                _effect.emit(AdminUIEffect.UpdateJson(prettyPrint(Json.parseToJsonElement(content) as JsonObject)))
            }
        }
    }

    fun handleAction(action: Action) {
        when (action) {
            is Action.Navigate -> {
                viewModelScope.launch {
                    _sideEffects.emit(UIEffect.NavigateEffect(action.destination))
                }
            }

            is Action.OpenUrl -> {
                viewModelScope.launch {
                    _sideEffects.emit(UIEffect.OpenUrlEffect(action.url))
                }
            }

            else -> {

            }
        }
    }

    fun sendToReview(json: String) {
        viewModelScope.launch {
            adminApi.saveScreen("AvitoPayCart", removeComments(json))
        }
    }
}