package ru.aleksandra.core.sdui.presentation.mapper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import ru.aleksandra.core.sdui.domain.model.Action
import ru.aleksandra.core.sdui.domain.model.BorderStroke
import ru.aleksandra.core.sdui.domain.model.ButtonColors
import ru.aleksandra.core.sdui.domain.model.ButtonElevation
import ru.aleksandra.core.sdui.domain.model.CheckboxColors
import ru.aleksandra.core.sdui.domain.model.Offset
import ru.aleksandra.core.sdui.domain.model.PaddingValues
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain
import ru.aleksandra.core.sdui.domain.model.Shadow
import ru.aleksandra.core.sdui.domain.model.Shape
import ru.aleksandra.core.sdui.domain.model.TextGeometricTransform
import ru.aleksandra.core.sdui.domain.model.TextIndent
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent
import androidx.compose.ui.geometry.Offset as OffsetUi
import androidx.compose.ui.text.style.TextIndent as TextIndentUi
import ru.aleksandra.core.sdui.presentation.model.Action as ActionUi

fun SDUIComponentDomain.toUi(data: Map<String, Any>): SDUIComponent {
    return when (this) {
        is SDUIComponentDomain.BottomBar -> TODO()
        is SDUIComponentDomain.Box -> TODO()
        is SDUIComponentDomain.Button -> toUi(data)
        is SDUIComponentDomain.Card -> TODO()
        is SDUIComponentDomain.Checkbox -> toUi()
        is SDUIComponentDomain.Column -> toUi(data)
        is SDUIComponentDomain.Divider -> TODO()
        is SDUIComponentDomain.FloatingActionButton -> TODO()
        is SDUIComponentDomain.Icon -> TODO()
        is SDUIComponentDomain.IconButton -> TODO()
        is SDUIComponentDomain.Image -> TODO()
        is SDUIComponentDomain.LazyColumn -> TODO()
        is SDUIComponentDomain.LazyRow -> TODO()
        is SDUIComponentDomain.OutlinedButton -> TODO()
        is SDUIComponentDomain.Row -> toUi(data)
        is SDUIComponentDomain.Scaffold -> TODO()
        is SDUIComponentDomain.Spacer -> TODO()
        is SDUIComponentDomain.Surface -> TODO()
        is SDUIComponentDomain.Text -> toUi(data)
        is SDUIComponentDomain.TextField -> TODO()
        is SDUIComponentDomain.AvitoCheckBox -> toUi()
        is SDUIComponentDomain.AvitoNavBar -> toUi(data)
        is SDUIComponentDomain.AvitoSelectAll -> toUi()
    }

}

fun SDUIComponentDomain.AvitoSelectAll.toUi(): SDUIComponent.AvitoSelectAll {
    return SDUIComponent.AvitoSelectAll(
        isChecked = isChecked,
        deleteCount = deleteCount,
        action = action.toUi()
    )
}

fun SDUIComponentDomain.AvitoCheckBox.toUi(): SDUIComponent.AvitoCheckBox {
    return SDUIComponent.AvitoCheckBox(
        text = text,
        isChecked = isChecked,
        action = action.toUi()
    )
}

fun SDUIComponentDomain.AvitoNavBar.toUi(data: Map<String, Any>): SDUIComponent.AvitoNavBar {
    return SDUIComponent.AvitoNavBar(
        title = title.getValue(data),
        action = action.toUi(),
    )
}

fun SDUIComponentDomain.Checkbox.toUi(): SDUIComponent.Checkbox {
    return SDUIComponent.Checkbox(
        checked = checked,
        action = action.toUi(),
        enabled = enabled,
        colors = colors.toUi(),
    )
}

fun SDUIComponentDomain.Column.toUi(data: Map<String, Any>): SDUIComponent.Column {
    return SDUIComponent.Column(
        action = action.toUi(),
        children = children.map { it.toUi(data) },
        verticalArrangement = verticalArrangement?.toArrangementVertical() ?: Arrangement.Top,
        horizontalAlignment = horizontalAlignment?.toAlignmentHorizontal() ?: Alignment.Start,
    )
}

fun SDUIComponentDomain.Row.toUi(data: Map<String, Any>): SDUIComponent.Row {
    return SDUIComponent.Row(
        action = action.toUi(),
        children = children.map { it.toUi(data) },
        horizontalArrangement = horizontalArrangement.toArrangementHorizontal(),
        verticalAlignment = verticalAlignment.toAlignmentVertical(),
    )
}

fun SDUIComponentDomain.Text.toUi(data: Map<String, Any>): SDUIComponent.Text {
    return SDUIComponent.Text(
        action = action.toUi(),
        text = text.getValue(data),
        color = color?.toColor() ?: Color.Unspecified,
        fontSize = fontSize?.toTextUnit() ?: TextUnit.Unspecified,
        letterSpacing = letterSpacing?.toTextUnit() ?: TextUnit.Unspecified,
        fontStyle = fontStyle?.toFontStyle(),
        fontWeight = fontWeight?.toFontWeight(),
        fontFamily = fontFamily?.toFontFamily(),
        textDecoration = textDecoration?.toTextDecoration(),
        textAlign = textAlign?.toTextAlign(),
        lineHeight = lineHeight?.toTextUnit() ?: TextUnit.Unspecified,
        overflow = overflow?.toTextOverflow() ?: TextOverflow.Clip,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
    )
}

fun SDUIComponentDomain.Button.toUi(data: Map<String, Any>): SDUIComponent.Button {
    return SDUIComponent.Button(
        action = action.toUi(),
        enabled = enabled,
        shape = shape?.toCompose(),
        colors = colors?.toCompose(),
        elevation = elevation?.toCompose(),
        border = border?.toCompose(),
        contentPadding = contentPadding?.toCompose(),
        content = content.toUi(data)
    )
}

fun Action.toUi(): ActionUi =
    when (this) {
        is Action.Navigate -> ActionUi.Navigate(destination)
        Action.None -> ActionUi.None
        is Action.OpenUrl -> ActionUi.OpenUrl(url)
        is Action.ShowToast -> ActionUi.ShowToast(message)
    }

fun Shape.toCompose() = when (this) {
    is Shape.CircleShape -> CircleShape
    is Shape.RoundedCornerShape -> RoundedCornerShape(cornerRadius)
}

fun ButtonColors.toCompose(): ru.aleksandra.core.sdui.presentation.model.ButtonColors {
    return ru.aleksandra.core.sdui.presentation.model.ButtonColors(
        containerColor = disabledContainerColor?.toColor() ?: Color.Unspecified,
        contentColor = disabledContainerColor?.toColor() ?: Color.Unspecified,
        disabledContainerColor = disabledContainerColor?.toColor() ?: Color.Unspecified,
        disabledContentColor = disabledContentColor?.toColor() ?: Color.Unspecified,
    )
}

fun String.toColor(): Color {
    return when (length) {
        6 -> { // RRGGBB
            val r = substring(0, 2).toInt(16)
            val g = substring(2, 4).toInt(16)
            val b = substring(4, 6).toInt(16)
            Color(r, g, b)
        }

        8 -> { // AARRGGBB
            val a = substring(0, 2).toInt(16)
            val r = substring(2, 4).toInt(16)
            val g = substring(4, 6).toInt(16)
            val b = substring(6, 8).toInt(16)
            Color(r, g, b, a)
        }

        else -> error("Invalid color format: $this")
    }
}

fun ButtonElevation.toCompose(): ru.aleksandra.core.sdui.presentation.model.ButtonElevation {
    return ru.aleksandra.core.sdui.presentation.model.ButtonElevation(
        defaultElevation?.dp ?: 0.dp,
        pressedElevation?.dp ?: 0.dp,
        focusedElevation?.dp ?: 0.dp,
        hoveredElevation?.dp ?: 0.dp,
        disabledElevation?.dp ?: 0.dp
    )
}

fun BorderStroke.toCompose(): androidx.compose.foundation.BorderStroke {
    return androidx.compose.foundation.BorderStroke(width.dp, color.toColor())
}

fun PaddingValues.toCompose(): androidx.compose.foundation.layout.PaddingValues {
    return androidx.compose.foundation.layout.PaddingValues(start.dp, top.dp, end.dp, bottom.dp)
}

fun String.toAlignmentHorizontal(): Alignment.Horizontal {
    return when (this) {
        "CenterHorizontally" -> Alignment.CenterHorizontally
        "End" -> Alignment.End
        "Start" -> Alignment.Start
        else -> Alignment.Start
    }
}

fun String.toAlignment(): Alignment {
    return when (this) {
        "TopStart" -> Alignment.TopStart
        "TopCenter" -> Alignment.TopCenter
        "TopEnd" -> Alignment.TopEnd
        "CenterStart" -> Alignment.CenterStart
        "Center" -> Alignment.Center
        "CenterEnd" -> Alignment.CenterEnd
        "BottomStart" -> Alignment.BottomStart
        "BottomCenter" -> Alignment.BottomCenter
        "BottomEnd" -> Alignment.BottomEnd
        else -> error("Нет такого alignment: $this")
    }
}

fun String.toAlignmentVertical(): Alignment.Vertical {
    return when (this) {
        "CenterVertically" -> Alignment.CenterVertically
        "Bottom" -> Alignment.Bottom
        "Top" -> Alignment.Top
        else -> Alignment.Bottom
    }
}

fun String.toArrangementHorizontal(): Arrangement.Horizontal {
    return when (this) {
        "Center" -> Arrangement.Center
        "End" -> Arrangement.End
        "SpaceBetween" -> Arrangement.SpaceBetween
        "SpaceEvenly" -> Arrangement.SpaceEvenly
        "SpaceAround" -> Arrangement.SpaceAround
        "Start" -> Arrangement.Start
        else -> Arrangement.Start
    }
}

fun String.toArrangementVertical(): Arrangement.Vertical =
    when (this) {
        "Center" -> Arrangement.Center
        "SpaceBetween" -> Arrangement.SpaceBetween
        "SpaceEvenly" -> Arrangement.SpaceEvenly
        "SpaceAround" -> Arrangement.SpaceAround
        "Top" -> Arrangement.Top
        "Bottom" -> Arrangement.Bottom
        else -> Arrangement.Top
    }

fun String.toBaselineShift(): BaselineShift {
    return when (this) {
        "Superscript" -> BaselineShift.Superscript
        "Subscript" -> BaselineShift.Subscript
        else -> BaselineShift.None
    }
}

fun String.toFontFamily(): FontFamily {
    return when (this) {
        "Monospace" -> FontFamily.Monospace
        "Serif" -> FontFamily.Serif
        "SansSerif" -> FontFamily.SansSerif
        "Cursive" -> FontFamily.Cursive
        else -> FontFamily.Default
    }
}

fun String.toFontStyle(): FontStyle {
    return when (this) {
        "Italic" -> FontStyle.Italic
        else -> FontStyle.Normal
    }
}

fun String.toFontSynthesis() =
    when (this) {
        "None" -> FontSynthesis.None
        "Weight" -> FontSynthesis.Weight
        "Style" -> FontSynthesis.Style
        "All" -> FontSynthesis.All
        else -> FontSynthesis.None
    }

fun String.toFontWeight() =
    when (this) {
        "W100" -> FontWeight.W100
        "W200" -> FontWeight.W200
        "W300" -> FontWeight.W300
        "W400" -> FontWeight.W400
        "Normal" -> FontWeight.Normal
        "Medium" -> FontWeight.Medium
        "W500" -> FontWeight.W500
        "SemiBold" -> FontWeight.SemiBold
        "W600" -> FontWeight.W600
        "Bold" -> FontWeight.Bold
        "W700" -> FontWeight.W700
        "ExtraBold" -> FontWeight.ExtraBold
        "W800" -> FontWeight.W800
        "Black" -> FontWeight.Black
        "W900" -> FontWeight.W900
        else -> FontWeight.Normal
    }

fun List<String>.toLocaleList(): LocaleList {
    return LocaleList(this.map { Locale(it) })
}

fun Offset.toUi(): OffsetUi {
    return OffsetUi(
        x = x,
        y = y
    )
}

fun Shadow.toUi(): androidx.compose.ui.graphics.Shadow {
    return androidx.compose.ui.graphics.Shadow(
        color = color.toColor(),
        offset = offset.toUi(),
        blurRadius = blurRadius,
    )
}

fun String.toTextAlign() = when (this) {
    "Left" -> TextAlign.Left
    "Right" -> TextAlign.Right
    "Center" -> TextAlign.Center
    "Justify" -> TextAlign.Justify
    "Start" -> TextAlign.Start
    "End" -> TextAlign.End
    else -> TextAlign.Justify
}

fun String.toTextDecoration() = when (this) {
    "Underline" -> TextDecoration.Underline
    "LineThrough" -> TextDecoration.LineThrough
    else -> TextDecoration.None
}

fun String.toTextDirection() = when (this) {
    "Ltr" -> TextDirection.Ltr
    "Rtl" -> TextDirection.Rtl
    "Content" -> TextDirection.Content
    "ContentOrLtr" -> TextDirection.ContentOrLtr
    "ContentOrRtl" -> TextDirection.ContentOrRtl
    else -> TextDirection.Unspecified
}

fun TextGeometricTransform.toUi(): TextGeometricTransform {
    return TextGeometricTransform(
        scaleX = scaleX,
        skewX = skewX,
    )
}

fun String.toTextUnit(): TextUnit {
    return when {
        endsWith("sp") -> this.removeSuffix("sp").toFloat().sp
        endsWith("em") -> this.removeSuffix("em").toFloat().em
        else -> error("Неизвестная единица измерения: $this")
    }
}

fun TextIndent.toUi(): TextIndentUi {
    return TextIndentUi(
        firstLine = firstLine.toTextUnit(),
        restLine = restLine.toTextUnit(),
    )
}

fun String.toTextOverflow(): TextOverflow {
    return when (this) {
        "Clip" -> TextOverflow.Clip
        "Ellipsis" -> TextOverflow.Ellipsis
        "Visible" -> TextOverflow.Visible
        "StartEllipsis" -> TextOverflow.StartEllipsis
        "MiddleEllipsis" -> TextOverflow.MiddleEllipsis
        else -> error("Неизвестный TextOverflow: $this")
    }
}

fun CheckboxColors.toUi(): androidx.compose.material3.CheckboxColors {
    return androidx.compose.material3.CheckboxColors(
        checkedBoxColor = checkedBoxColor?.toColor() ?: Color.Unspecified,
        uncheckedBoxColor = uncheckedBoxColor?.toColor() ?: Color.Unspecified,
        checkedCheckmarkColor = checkedCheckmarkColor?.toColor() ?: Color.Unspecified,
        uncheckedCheckmarkColor = uncheckedCheckmarkColor?.toColor() ?: Color.Unspecified,
        checkedBorderColor = checkedBorderColor?.toColor() ?: Color.Unspecified,
        uncheckedBorderColor = uncheckedBorderColor?.toColor() ?: Color.Unspecified,
        disabledBorderColor = disabledBorderColor?.toColor() ?: Color.Unspecified,
        disabledCheckedBoxColor = disabledCheckedBoxColor?.toColor() ?: Color.Unspecified,
        disabledUncheckedBoxColor = disabledUncheckedBoxColor?.toColor() ?: Color.Unspecified,
        disabledUncheckedBorderColor = disabledUncheckedBorderColor?.toColor()
            ?: Color.Unspecified,
        disabledIndeterminateBoxColor = disabledIndeterminateBoxColor?.toColor()
            ?: Color.Unspecified,
        disabledIndeterminateBorderColor = disabledIndeterminateBorderColor?.toColor()
            ?: Color.Unspecified
    )
}