package ru.aleksandra.core.sdui.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
@SerialName("SDUIComponentDomain")
sealed class SDUIComponentDomain() {
    abstract val modifier: List<ModifierProperties>

    abstract val action: Action

    @Serializable
    @SerialName("Text")
    data class Text(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val text: BindableString,
        val style: String? = null,
        val color: ColorType? = null,
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
        val content: SDUIComponentDomain,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("IconButton")
    data class IconButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val content: SDUIComponentDomain,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("FloatingActionButton")
    data class FloatingActionButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
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
        val horizontalArrangement: String? = null,
        val verticalAlignment: String? = null,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Box")
    data class Box(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponentDomain>,
        val contentAlignment: String? = null,
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
        val url: DrawableType,
        val contentDescription: String? = null,
        val contentScale: String? = null,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("Icon")
    data class Icon(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val drawable: DrawableType,
        val tint: ColorType,
        val contentDescription: String? = null,
    ) : SDUIComponentDomain()

    // Containers

    @Serializable
    @SerialName("Surface")
    data class Surface(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponentDomain>
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
        val children: List<SDUIComponentDomain>
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
        val deleteCount: BindableInt,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("AvitoShopName")
    data class AvitoShopName(
        val isChecked: Boolean = false,
        val shopName: BindableString,
        val rating: BindableFloat,
        val reviewsCount: BindableInt,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("AvitoCartItem")
    data class AvitoCartItem(
        val isChecked: Boolean = false,
        val name: BindableString,
        val priceWithoutDiscount: BindableInt,
        val priceWithDiscount: BindableInt = BindableValue.Static(0),
        val salePercent: BindableInt = BindableValue.Static(0),
        val count: BindableInt,
        val imageUrl: BindableString,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponentDomain()

    @Serializable
    @SerialName("RepetitiveComponent")
    data class RepetitiveComponent(
        val content: SDUIComponentDomain,
        val itemsPath: String,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponentDomain()

    companion object {
        //TODO: Добавить все подклассы. Нельзя использовать рефлексию, т.к. она не поддерживается в wasm :(
        val subclasses: List<KClass<out SDUIComponentDomain>> = listOf(
            Text::class,
            Button::class,
            AvitoNavBar::class,
            RepetitiveComponent::class,
            TextField::class,
            Column::class,
            Row::class,
            Image::class,
            Icon::class,
            Spacer::class,
            Divider::class,
            Box::class,
            Scaffold::class,
            FloatingActionButton::class,
            IconButton::class,
            OutlinedButton::class,
            Checkbox::class,
            AvitoCheckBox::class,
            AvitoSelectAll::class,
            AvitoShopName::class,
            AvitoCartItem::class,
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
    @SerialName("Size")
    data class Size(val value: Int) : ModifierProperties()

    @Serializable
    @SerialName("Weight")
    data class Weight(val value: Float) : ModifierProperties()

    @Serializable
    @SerialName("FillMaxWidth")
    data class FillMaxWidth(val fraction: Float = 1f) : ModifierProperties()

    @Serializable
    @SerialName("FillMaxHeight")
    data class FillMaxHeight(val fraction: Float = 1f) : ModifierProperties()

    @Serializable
    @SerialName("FillMaxSize")
    data class FillMaxSize(val fraction: Float = 1f) : ModifierProperties()

    @Serializable
    @SerialName("WrapContentWidth")
    data class WrapContentWidth(val alignment: String) : ModifierProperties()

    @Serializable
    @SerialName("WrapContentHeight")
    data class WrapContentHeight(val alignment: String) : ModifierProperties()

    @Serializable
    @SerialName("MatchParentSize")
    data object MatchParentSize : ModifierProperties()

    @Serializable
    @SerialName("Background")
    data class Background(val color: ColorType, val shape: Shape) : ModifierProperties()

    @Serializable
    @SerialName("Border")
    data class Border(
        val width: Int,
        val color: String,
        val shape: Shape,
    ) : ModifierProperties()

    @Serializable
    @SerialName("Clip")
    data class Clip(val shape: Shape) : ModifierProperties()

    @Serializable
    @SerialName("Shadow")
    data class Shadow(
        val elevation: Int,
        val shape: Shape = Shape.RectangleShape,
        val clip: Boolean = false,
        val ambientColor: String? = null,
        val spotColor: String? = null
    ) : ModifierProperties()

    @Serializable
    @SerialName("Alpha")
    data class Alpha(val alpha: Float) : ModifierProperties()
}

@Serializable
@SerialName("PaddingProperties")
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
    @SerialName("PopBack")
    data object PopBack : Action()

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
    @Serializable
    @SerialName("CircleShape")
    data object CircleShape : Shape()

    @Serializable
    @SerialName("RoundedCornerShape")
    data class RoundedCornerShape(val radius: Int) : Shape()

    @Serializable
    @SerialName("RectangleShape")
    data object RectangleShape : Shape()
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
@SerialName("BindableValue")
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
    data class Dynamic<T>(
        val path: String,
        val transformation: Transformation? = null,
    ) : BindableValue<T>() {
        override fun getValue(dataContext: Map<String, Any?>): T {
            return dataContext[path] as T
        }
    }
}

@Serializable
@SerialName("Transformation")
sealed class Transformation {
    @Serializable
    @SerialName("Format")
    data class Format(val pattern: String) : Transformation()
}

@Serializable
@SerialName("Color")
sealed class ColorType {

    @Serializable
    @SerialName("Theme")
    data class ThemeColor(val name: String) : ColorType()

    @Serializable
    @SerialName("Static")
    data class StaticColor(val value: String) : ColorType()
}

@Serializable
@SerialName("Drawable")
sealed class DrawableType {

    @Serializable
    @SerialName("Theme")
    data class ThemeDrawable(val name: String) : DrawableType()

    @Serializable
    @SerialName("Dynamic")
    data class DynamicDrawable(val path: String) : DrawableType()

    @Serializable
    @SerialName("Static")
    data class StaticDrawable(val value: String) : DrawableType()
}

/*@Serializable
sealed class TypoType {

    @Serializable
    @SerialName("Theme")
    data class ThemeTypo(val name: String) : TypoType()

    @Serializable
    @SerialName("Static")
    data class StaticTypo(val value: String) : TypoType()
}*/


typealias BindableString = BindableValue<String>
typealias BindableInt = BindableValue<Int>
typealias BindableBoolean = BindableValue<Boolean>
typealias BindableDouble = BindableValue<Double>
typealias BindableFloat = BindableValue<Float>
typealias BindableLong = BindableValue<Long>
typealias BindableList<T> = BindableValue<List<T>>
typealias BindableMap<K, V> = BindableValue<Map<K, V>>