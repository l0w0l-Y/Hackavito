package ru.aleksandra.core.sdui.presentation.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
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
        val color: Color = Color.Unspecified,
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
        /* TODO: Добавить onTextLayout в UI */
        //val onTextLayout: ((TextLayoutResult) -> Unit)? = null,
        //val style: StyleProperties.TextStyleProperties? = null,
    ) : SDUIComponent()

    @Serializable
    @SerialName("TextField")
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

    @Serializable
    @SerialName("OutlinedButton")
    data class OutlinedButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val title: String,
    ) : SDUIComponent()

    @Serializable
    @SerialName("IconButton")
    data class IconButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val iconUrl: String,
    ) : SDUIComponent()

    @Serializable
    @SerialName("FloatingActionButton")
    data class FloatingActionButton(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val iconUrl: String,
    ) : SDUIComponent()

    // Layout components
    @Serializable
    @SerialName("Column")
    data class Column(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>,
        @Serializable(with = ArrangementVerticalSerializer::class)
        val verticalArrangement: Arrangement.Vertical = Arrangement.Top,
        @Serializable(with = AlignmentHorizontalSerializer::class)
        val horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    ) : SDUIComponent()

    @Serializable
    @SerialName("Row")
    data class Row(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>,
        @Serializable(with = ArrangementHorizontalSerializer::class)
        val horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
        @Serializable(with = AlignmentVerticalSerializer::class)
        val verticalAlignment: Alignment.Vertical = Alignment.Top,
    ) : SDUIComponent()

    @Serializable
    @SerialName("Box")
    data class Box(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>,
        @Serializable(with = AlignmentSerializer::class)
        val contentAlignment: Alignment = Alignment.TopStart,
        val propagateMinConstraints: Boolean = false,
    ) : SDUIComponent()

    @Serializable
    @SerialName("LazyColumn")
    data class LazyColumn(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    @Serializable
    @SerialName("LazyRow")
    data class LazyRow(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    @Serializable
    @SerialName("Scaffold")
    data class Scaffold(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val topBar: SDUIComponent?,
        val content: SDUIComponent
    ) : SDUIComponent()

    // Utility components
    @Serializable
    @SerialName("Spacer")
    data class Spacer(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val height: Int
    ) : SDUIComponent()

    @Serializable
    @SerialName("Divider")
    data class Divider(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val thickness: Int? = null,
        @Serializable(with = ColorSerializer::class)
        val color: Color? = null,
        val type: DividerType
    ) : SDUIComponent() {
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
        SDUIComponent()

    @Serializable
    @SerialName("Icon")
    data class Icon(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val url: String
    ) : SDUIComponent()

    // Containers
    @Serializable
    @SerialName("Card")
    data class Card(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    @Serializable
    @SerialName("Surface")
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

    @Serializable
    @SerialName("BottomBar")
    data class BottomBar(
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    @Serializable
    data class AvitoNavBar(
        val title: String,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    @Serializable
    data class AvitoCheckBox(
        val text: String,
        val isChecked: Boolean = false,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()

    @Serializable
    data class AvitoSelectAll(
        val isChecked: Boolean = false,
        val deleteCount: Int = 0,
        override val modifier: List<ModifierProperties> = emptyList(),
        override val action: Action = Action.None,
    ) : SDUIComponent()
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
    @SerialName("CallApi")
    data class CallApi(val endpoint: String, val method: String = "GET", val body: String? = null) :
        Action()

    @Serializable
    @SerialName("Close")
    data object Close : Action()

    @Serializable
    @SerialName("None")
    data object None : Action()
}

data class ButtonColors(
    val containerColor: Color? = null,
    val contentColor: Color? = null,
    val disabledContainerColor: Color? = null,
    val disabledContentColor: Color? = null,
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