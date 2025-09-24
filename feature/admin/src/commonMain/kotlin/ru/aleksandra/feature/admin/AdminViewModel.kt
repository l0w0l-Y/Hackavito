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
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCase
import ru.aleksandra.core.sdui.presentation.mapper.toUi
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.UIEffect
import ru.aleksandra.core.sdui.presentation.model.UIState
import kotlin.collections.iterator

class AdminViewModel(
    val loadUIUseCase: LoadUIUseCase
) : ViewModel() {
    private var _ui = MutableStateFlow<UIState>(UIState.Init)
    val ui: StateFlow<UIState> = _ui

    private val _sideEffects: MutableSharedFlow<UIEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<UIEffect> = _sideEffects

    private var _uiElements = MutableStateFlow<List<String>>(emptyList())
    val uiElements: StateFlow<List<String>> = _uiElements

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
                _effect.emit(AdminUIEffect.UpdateJson(jsonModel.toString()))
            }
        }
    }

    @OptIn(InternalSerializationApi::class)
    fun getAllUIJson() {
        viewModelScope.launch {
            _uiElements.value =
                SDUIComponentDomain.subclasses.map { it.serializer().descriptor.serialName }
        }
    }

    fun <T> toJsonModel(serializer: KSerializer<T>, value: T?): JsonObject {
        val json = Json { encodeDefaults = false }
        val baseJson = if (value != null) {
            json.encodeToJsonElement(serializer, value).jsonObject
        } else {
            buildJsonObject { }
        }

        val descriptor = serializer.descriptor
        val withRequired = buildJsonObject {
            put("type", JsonPrimitive(descriptor.serialName))

            for ((k, v) in baseJson) {
                put(k, v)
            }

            // проверяем обязательные поля
            for (i in 0 until descriptor.elementsCount) {
                val name = descriptor.getElementName(i)
                val isOptional = descriptor.isElementOptional(i)

                if (!isOptional) {
                    // обязательное поле отсутствует → вставляем заглушку
                    val kind = descriptor.getElementDescriptor(i).kind
                    val defaultValue = when (kind) {
                        PrimitiveKind.STRING -> JsonPrimitive("")
                        PrimitiveKind.INT -> JsonPrimitive(0)
                        PrimitiveKind.LONG -> JsonPrimitive(0L)
                        PrimitiveKind.BOOLEAN -> JsonPrimitive(false)
                        PrimitiveKind.FLOAT -> JsonPrimitive(0f)
                        PrimitiveKind.DOUBLE -> JsonPrimitive(0.0)
                        StructureKind.LIST -> JsonArray(emptyList())
                        StructureKind.CLASS -> JsonObject(emptyMap())
                        else -> JsonNull
                    }
                    put(name, defaultValue)
                }
            }
        }

        return withRequired
    }

    fun loadFromJson(newJson: String) {
        viewModelScope.launch {
            _ui.value = UIState.Loading
            val json = Json {
                classDiscriminator = "type"
            }
            runCatching { json.decodeFromString<SDUIComponentDomain>(newJson) }
                .onSuccess { _ui.value = UIState.Loaded(it.toUi()) }
                .onFailure { _ui.value = UIState.Error(it.message ?: "Неизвестная ошибка") }
        }
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
                _effect.emit(AdminUIEffect.UpdateJson(content))
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
}