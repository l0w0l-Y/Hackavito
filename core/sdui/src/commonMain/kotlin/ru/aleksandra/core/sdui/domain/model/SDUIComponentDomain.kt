package ru.aleksandra.core.sdui.domain.model

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.aleksandra.core.sdui.presentation.model.PaddingProperties
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent
import ru.aleksandra.core.sdui.presentation.serializer.ColorSerializer
import kotlin.reflect.KClass

@Serializable
sealed class SDUIComponentDomain() {
    abstract val modifier: List<ModifierProperties>

    abstract val action: Action

    @Serializable
    @SerialName("Text")
    data class Text(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val text: BindableString,
        val color: String? = null,
        val fontSize: String? = null,
        val fontStyle: String? = null,
        val fontWeight: String? = null,
        val fontFamily: String? = null,
        val letterSpacing: String? = null,
        val textDecoration: String? = null,
        val textAlign: String? = null,
        val lineHeight: String? = null,
        val overflow: String? = null,
        val softWrap: Boolean = true,
        val maxLines: Int = Int.MAX_VALUE,
        val minLines: Int = 1,
        //val onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("TextField")
    data class TextField(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val hint: String,
        val value: String,
    ) : SDUIComponentDomain()

    // Button components
    @Serializable
    @SerialName("Button")
    data class Button(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val content: SDUIComponentDomain,
        val enabled: Boolean = true,
        val shape: Shape? = null,
        val colors: ButtonColors? = null,
        val elevation: ButtonElevation? = null,
        val border: BorderStroke? = null,
        val contentPadding: PaddingValues? = null,
        //val interactionSource: MutableInteractionSource? = null,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("OutlinedButton")
    data class OutlinedButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val title: String,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("IconButton")
    data class IconButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val iconUrl: String,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("FloatingActionButton")
    data class FloatingActionButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val iconUrl: String,
    ) : SDUIComponentDomain()

    // Layout components
    @Serializable
    @SerialName("Column")
    data class Column(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponentDomain>,
        val verticalArrangement: String? = null,
        val horizontalAlignment: String? = null,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Row")
    data class Row(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponentDomain>,
        val horizontalArrangement: String,
        val verticalAlignment: String,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Box")
    data class Box(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponentDomain>,
        val contentAlignment: String,
        val propagateMinConstraints: Boolean = false,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("LazyColumn")
    data class LazyColumn(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponentDomain>
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("LazyRow")
    data class LazyRow(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponentDomain>
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Scaffold")
    data class Scaffold(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val topBar: SDUIComponentDomain?,
        val content: SDUIComponentDomain
    ) : SDUIComponentDomain()

    // Utility components
    @Serializable
    @SerialName("Spacer")
    data class Spacer(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val height: Int
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Divider")
    data class Divider(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val thickness: Int? = null,
        val color: String? = null,
        val type: DividerType
    ) : SDUIComponentDomain() {
        enum class DividerType {
            HORIZONTAL, VERTICAL
        }
    }

    // Media components
    @Serializable
    @SerialName("Image")
    data class Image(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val url: String
    ) :
        SDUIComponentDomain()

    @Serializable
    @SerialName("Icon")
    data class Icon(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val url: String
    ) : SDUIComponentDomain()

    // Containers
    @Serializable
    @SerialName("Card")
    data class Card(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Surface")
    data class Surface(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Checkbox")
    data class Checkbox(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val checked: Boolean,
        val enabled: Boolean = true,
        val colors: CheckboxColors,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("BottomBar")
    data class BottomBar(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("AvitoNavBar")
    data class AvitoNavBar(
        val title: BindableString,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("AvitoCheckBox")
    data class AvitoCheckBox(
        val text: String,
        val isChecked: Boolean = false,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("AvitoSelectAll")
    data class AvitoSelectAll(
        val isChecked: Boolean = false,
        val deleteCount: Int = 0,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponentDomain()

    companion object {
        //TODO: Добавить все подклассы. Нельзя использовать рефлексию, т.к. она не поддерживается в wasm :(
        val subclasses: List<KClass<out SDUIComponentDomain>> = listOf(
            Text::class,
            Button::class,
            AvitoNavBar::class
        )
    }
}

@Serializable
sealed class ModifierProperties {
    @Serializable
    @SerialName("Width")
    data class Width(val value: Int) : ModifierProperties()

    @Serializable
    @SerialName("Height")
    data class Height(val value: Int) : ModifierProperties()

    @Serializable
    @SerialName("Padding")
    data class Padding(val value: PaddingProperties) : ModifierProperties()

    @Serializable
    @SerialName("Clickable")
    data class Clickable(val value: Boolean) : ModifierProperties()

    @Serializable
    @SerialName("BackgroundColor")
    data class BackgroundColor(@Serializable(with = ColorSerializer::class) val color: Color) :
        ModifierProperties()
}

@Serializable
data class PaddingProperties(
    val start: Int,
    val top: Int,
    val end: Int,
    val bottom: Int
) {
    constructor(all: Int) : this(all, all, all, all)
    constructor(horizontal: Int, vertical: Int) : this(horizontal, vertical, horizontal, vertical)
}

@Serializable
sealed class Action {
    @Serializable
    @SerialName("OpenUrl")
    data class OpenUrl(val url: String) : Action()

    @Serializable
    @SerialName("ShowToast")
    data class ShowToast(val message: String) : Action()

    @Serializable
    @SerialName("Navigate")
    data class Navigate(val destination: String) : Action()

    @Serializable
    @SerialName("None")
    data object None : Action()
}

sealed class StyleProperties {

    data class ButtonStyleProperties(
        val backgroundColor: String? = null,
        val contentColor: String? = null,
        val disabledBackgroundColor: String? = null,
        val disabledContentColor: String? = null
    ) : StyleProperties()
}

@Serializable
sealed class Shape() {
    data object CircleShape : Shape()
    data class RoundedCornerShape(val cornerRadius: Int) : Shape()
}

@Serializable
data class ButtonColors(
    val containerColor: String? = null,
    val contentColor: String? = null,
    val disabledContainerColor: String? = null,
    val disabledContentColor: String? = null,
)

@Serializable
data class ButtonElevation(
    val defaultElevation: Int? = null,
    val pressedElevation: Int? = null,
    val focusedElevation: Int? = null,
    val hoveredElevation: Int? = null,
    val disabledElevation: Int? = null,
)

@Serializable
data class BorderStroke(
    val width: Int,
    val color: String,
)

@Serializable
data class PaddingValues(
    val start: Int,
    val top: Int,
    val end: Int,
    val bottom: Int,
) {
    constructor(all: Int) : this(all, all, all, all)
    constructor(horizontal: Int, vertical: Int) : this(horizontal, vertical, horizontal, vertical)
}

@Serializable
data class Offset(
    val x: Float,
    val y: Float,
)

@Serializable
data class Shadow(
    val color: String,
    val offset: Offset,
    val blurRadius: Float
)

@Serializable
data class TextGeometricTransform(
    val scaleX: Float,
    val skewX: Float
)

@Serializable
data class TextIndent(
    val firstLine: String,
    val restLine: String,
)

@Serializable
data class CheckboxColors(
    val checkedCheckmarkColor: String?,
    val uncheckedCheckmarkColor: String?,
    val checkedBoxColor: String?,
    val uncheckedBoxColor: String?,
    val disabledCheckedBoxColor: String?,
    val disabledUncheckedBoxColor: String?,
    val disabledIndeterminateBoxColor: String?,
    val checkedBorderColor: String?,
    val uncheckedBorderColor: String?,
    val disabledBorderColor: String?,
    val disabledUncheckedBorderColor: String?,
    val disabledIndeterminateBorderColor: String?
)

@Serializable
sealed class BindableValue<T> {
    abstract fun getValue(dataContext: Map<String, Any?>): T

    @Serializable
    @SerialName("Static")
    data class Static<T>(val value: T) : BindableValue<T>() {
        override fun getValue(dataContext: Map<String, Any?>): T = value
    }

    @Suppress("UNCHECKED_CAST")
    @Serializable
    @SerialName("Dynamic")
    data class Dynamic<T>(val path: String) : BindableValue<T>() {
        override fun getValue(dataContext: Map<String, Any?>): T {
            return dataContext[path] as T
        }
    }
}

typealias BindableString = BindableValue<String>
typealias BindableInt = BindableValue<Int>
typealias BindableBoolean = BindableValue<Boolean>
typealias BindableDouble = BindableValue<Double>
typealias BindableFloat = BindableValue<Float>
typealias BindableLong = BindableValue<Long>
typealias BindableList<T> = BindableValue<List<T>>
typealias BindableMap<K, V> = BindableValue<Map<K, V>>