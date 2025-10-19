package ru.aleksandra.core.sdui.presentation.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import ru.aleksandra.core.sdui.presentation.serializer.AlignmentHorizontalSerializer
import ru.aleksandra.core.sdui.presentation.serializer.AlignmentSerializer
import ru.aleksandra.core.sdui.presentation.serializer.AlignmentVerticalSerializer
import ru.aleksandra.core.sdui.presentation.serializer.ArrangementHorizontalSerializer
import ru.aleksandra.core.sdui.presentation.serializer.ArrangementVerticalSerializer
import ru.aleksandra.core.sdui.presentation.serializer.BaselineShiftSerializer
import ru.aleksandra.core.sdui.presentation.serializer.ColorSerializer
import ru.aleksandra.core.sdui.presentation.serializer.FontFamilySerializer
import ru.aleksandra.core.sdui.presentation.serializer.FontStyleSerializer
import ru.aleksandra.core.sdui.presentation.serializer.FontSynthesisSerializer
import ru.aleksandra.core.sdui.presentation.serializer.FontWeightSerializer
import ru.aleksandra.core.sdui.presentation.serializer.LocaleListSerializer
import ru.aleksandra.core.sdui.presentation.serializer.ShadowSerializer
import ru.aleksandra.core.sdui.presentation.serializer.TextAlignSerializer
import ru.aleksandra.core.sdui.presentation.serializer.TextDecorationSerializer
import ru.aleksandra.core.sdui.presentation.serializer.TextDirectionSerializer
import ru.aleksandra.core.sdui.presentation.serializer.TextGeometricTransformSerializer
import ru.aleksandra.core.sdui.presentation.serializer.TextIndentSerializer
import ru.aleksandra.core.sdui.presentation.serializer.TextUnitSerializer

@Serializable
sealed class SDUIComponent() {
    abstract val modifier: List<ModifierProperties>

    abstract val action: Action

    data class Text(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val text: String,
        val color: ColorType?,
        val fontSize: TextUnit = TextUnit.Unspecified,
        val fontStyle: FontStyle? = null,
        val fontWeight: FontWeight? = null,
        val fontFamily: FontFamily? = null,
        val letterSpacing: TextUnit = TextUnit.Unspecified,
        val textDecoration: TextDecoration? = null,
        val textAlign: TextAlign? = null,
        val lineHeight: TextUnit = TextUnit.Unspecified,
        val overflow: TextOverflow = TextOverflow.Clip,
        val softWrap: Boolean = true,
        val maxLines: Int = Int.MAX_VALUE,
        val minLines: Int = 1,
        val style: String? = null,
        /* TODO: Добавить onTextLayout в UI */
        //val onTextLayout: ((TextLayoutResult) -> Unit)? = null,
        //val style: StyleProperties.TextStyleProperties? = null,
    ) : SDUIComponent()

    data class TextField(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val hint: String,
        val value: String,
    ) : SDUIComponent()

    data class Button(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val content: SDUIComponent,
        val enabled: Boolean = true,
        val shape: Shape? = null,
        val colors: ButtonColors? = null,
        val elevation: ButtonElevation? = null,
        val border: androidx.compose.foundation.BorderStroke? = null,
        val contentPadding: androidx.compose.foundation.layout.PaddingValues? = null,
    ) : SDUIComponent()

    data class OutlinedButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val title: String,
        val enabled: Boolean = true,
        val content: SDUIComponent,
    ) : SDUIComponent()

    data class IconButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val content: SDUIComponent,
    ) : SDUIComponent()

    data class FloatingActionButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    // Layout components
    data class Column(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>,
        val verticalArrangement: Arrangement.Vertical,
        val horizontalAlignment: Alignment.Horizontal,
    ) : SDUIComponent()

    data class Row(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>,
        val horizontalArrangement: Arrangement.Horizontal,
        val verticalAlignment: Alignment.Vertical,
    ) : SDUIComponent()

    data class Box(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>,
        val contentAlignment: Alignment = Alignment.TopStart,
        val propagateMinConstraints: Boolean = false,
    ) : SDUIComponent()

    data class LazyColumn(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class LazyRow(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class Scaffold(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val topBar: SDUIComponent?,
        val content: SDUIComponent
    ) : SDUIComponent()

    // Utility components
    data class Spacer(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    data class Divider(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val thickness: Dp? = null,
        val color: Color? = null,
        val type: DividerType
    ) : SDUIComponent() {
        enum class DividerType {
            HORIZONTAL, VERTICAL
        }
    }

    // Media components

    data class Image(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val url: DrawableType,
        val contentDescription: String?,
        val contentScale: ContentScale = ContentScale.Fit,
    ) :
        SDUIComponent()

    //TODO: Разделить на Классы tint и drawable (чтобы не передавать лишнее)
    data class Icon(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val drawable: DrawableType,
        val tint: ColorType,
        val contentDescription: String?
    ) : SDUIComponent()

    // Containers

    data class Surface(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class Checkbox(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val checked: Boolean,
        val enabled: Boolean = true,
        val colors: androidx.compose.material3.CheckboxColors?,
        //interactionSource: MutableInteractionSource? = null
    ) : SDUIComponent()

    data class BottomBar(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class AvitoNavBar(
        val title: String,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    data class AvitoCheckBox(
        val text: String,
        val isChecked: Boolean = false,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    data class AvitoSelectAll(
        val isChecked: Boolean = false,
        val deleteCount: Int,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    data class AvitoShopName(
        val isChecked: Boolean = false,
        val shopName: String,
        val rating: Float,
        val reviewsCount: Int,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    data class AvitoCartItem(
        val isChecked: Boolean = false,
        val name: String,
        val priceWithoutDiscount: Int,
        val priceWithDiscount: Int,
        val salePercent: Int,
        val count: Int,
        val imageUrl: String,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    data class RepetitiveComponent(
        val component: List<SDUIComponent>,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()
}

sealed class ModifierProperties {
    data class Width(val value: Dp) : ModifierProperties()

    data class Height(val value: Dp) : ModifierProperties()

    data class Weight(val value: Float) : ModifierProperties()

    data class Padding(val value: PaddingProperties) : ModifierProperties()

    data class Size(val size: Dp) : ModifierProperties()

    data class FillMaxWidth(val fraction: Float = 1f) : ModifierProperties()

    data class FillMaxHeight(val fraction: Float = 1f) : ModifierProperties()

    data class FillMaxSize(val fraction: Float = 1f) : ModifierProperties()

    data class WrapContentWidth(val alignment: Alignment.Horizontal = Alignment.CenterHorizontally) :
        ModifierProperties()

    data class WrapContentHeight(val alignment: Alignment.Vertical = Alignment.CenterVertically) :
        ModifierProperties()

    data class Background(val color: ColorType, val shape: Shape) : ModifierProperties()

    data class Border(
        val width: Dp,
        val color: Color,
        val shape: Shape
    ) : ModifierProperties()

    data class Clip(val shape: Shape) : ModifierProperties()

    data class Shadow(
        val elevation: Dp,
        val shape: Shape = RectangleShape,
        val clip: Boolean = false,
        val ambientColor: Color = Color.Unspecified,
        val spotColor: Color = Color.Unspecified
    ) : ModifierProperties()

    data class Alpha(val alpha: Float) : ModifierProperties()

    data object MatchParentSize : ModifierProperties()

    data class Align(val alignment: Alignment) : ModifierProperties()

    data class Clickable(val action: Action) : ModifierProperties()
}


data class PaddingProperties(
    val start: Dp,
    val top: Dp,
    val end: Dp,
    val bottom: Dp
) {
    constructor(all: Dp) : this(all, all, all, all)
    constructor(horizontal: Dp, vertical: Dp) : this(horizontal, vertical, horizontal, vertical)
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
    @SerialName("CallApi")
    data class CallApi(val endpoint: String, val method: String = "GET", val body: String? = null) :
        Action()

    @Serializable
    @SerialName("Close")
    data object Close : Action()

    @Serializable
    @SerialName("PopBack")
    data object PopBack : Action()

    @Serializable
    @SerialName("Custom")
    data class Custom(val name: String) : Action()

    @Serializable
    @SerialName("None")
    data object None : Action()
}

data class ButtonColors(
    val containerColor: ColorType? = null,
    val contentColor: ColorType? = null,
    val disabledContainerColor: ColorType? = null,
    val disabledContentColor: ColorType? = null,
)

data class ButtonElevation(
    val defaultElevation: Dp? = null,
    val pressedElevation: Dp? = null,
    val focusedElevation: Dp? = null,
    val hoveredElevation: Dp? = null,
    val disabledElevation: Dp? = null,
)

sealed class StyleProperties {
    @Serializable
    data class TextStyleProperties(
        @Serializable(with = ColorSerializer::class)
        val color: Color = Color.Unspecified,
        @Serializable(with = TextUnitSerializer::class)
        val fontSize: TextUnit = TextUnit.Unspecified,
        @Serializable(with = FontWeightSerializer::class)
        val fontWeight: FontWeight? = null,
        @Serializable(with = FontStyleSerializer::class)
        val fontStyle: FontStyle? = null,
        @Serializable(with = FontSynthesisSerializer::class)
        val fontSynthesis: FontSynthesis? = null,
        @Serializable(with = FontFamilySerializer::class)
        val fontFamily: FontFamily? = null,
        val fontFeatureSettings: String? = null,
        @Serializable(with = TextUnitSerializer::class)
        val letterSpacing: TextUnit = TextUnit.Unspecified,
        @Serializable(with = BaselineShiftSerializer::class)
        val baselineShift: BaselineShift? = null,
        @Serializable(with = TextGeometricTransformSerializer::class)
        val textGeometricTransform: TextGeometricTransform? = null,
        @Serializable(with = LocaleListSerializer::class)
        val localeList: LocaleList? = null,
        @Serializable(with = ColorSerializer::class)
        val background: Color = Color.Unspecified,
        @Serializable(with = TextDecorationSerializer::class)
        val textDecoration: TextDecoration? = null,
        @Serializable(with = ShadowSerializer::class)
        val shadow: Shadow? = null,
        @Serializable(with = TextAlignSerializer::class)
        val textAlign: TextAlign = TextAlign.Unspecified,
        @Serializable(with = TextDirectionSerializer::class)
        val textDirection: TextDirection? = null,
        @Serializable(with = TextUnitSerializer::class)
        val lineHeight: TextUnit = TextUnit.Unspecified,
        @Serializable(with = TextIndentSerializer::class)
        val textIndent: TextIndent? = null,
    ) : StyleProperties()

    data class ButtonStyleProperties(
        val backgroundColor: String? = null,
        val contentColor: String? = null,
        val disabledBackgroundColor: String? = null,
        val disabledContentColor: String? = null
    ) : StyleProperties()
}

sealed class ColorType {

    data class ThemeColor(val name: String) : ColorType()

    data class StaticColor(val value: Color) : ColorType()
}

sealed class DrawableType {

    data class ThemeDrawable(val resource: DrawableResource) : DrawableType()

    data class StaticDrawable(val value: String) : DrawableType()

    data class DynamicDrawable(val value: String) : DrawableType()
}