package ru.aleksandra.core.sdui.presentation.mapper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
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
import kotlinx.serialization.json.JsonElement
import ru.aleksandra.core.sdui.domain.model.Action
import ru.aleksandra.core.sdui.domain.model.BindableString
import ru.aleksandra.core.sdui.domain.model.BindableValue
import ru.aleksandra.core.sdui.domain.model.BorderStroke
import ru.aleksandra.core.sdui.domain.model.ButtonColors
import ru.aleksandra.core.sdui.domain.model.ButtonElevation
import ru.aleksandra.core.sdui.domain.model.CheckboxColors
import ru.aleksandra.core.sdui.domain.model.ColorType
import ru.aleksandra.core.sdui.domain.model.Offset
import ru.aleksandra.core.sdui.domain.model.PaddingValues
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain
import ru.aleksandra.core.sdui.domain.model.Shadow
import ru.aleksandra.core.sdui.domain.model.Shape
import ru.aleksandra.core.sdui.domain.model.TextGeometricTransform
import ru.aleksandra.core.sdui.domain.model.TextIndent
import ru.aleksandra.core.sdui.presentation.model.DrawableType
import ru.aleksandra.core.sdui.presentation.model.ModifierProperties
import ru.aleksandra.core.sdui.presentation.model.PaddingProperties
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent
import ru.aleksandra.core.ui.toDrawable
import androidx.compose.ui.geometry.Offset as OffsetUi
import androidx.compose.ui.text.style.TextIndent as TextIndentUi
import ru.aleksandra.core.sdui.domain.model.ModifierProperties as ModifierPropertiesDomain
import ru.aleksandra.core.sdui.presentation.model.Action as ActionUi

//TODO: Добавить метод с data map<String, Any> для динамических данных прям с сервера
fun SDUIComponentDomain.toUi(json: JsonElement): SDUIComponent {
    return when (this) {
        is SDUIComponentDomain.BottomBar -> toUi(json)
        is SDUIComponentDomain.Box -> toUi(json)
        is SDUIComponentDomain.Button -> toUi(json)
        is SDUIComponentDomain.Checkbox -> toUi(json)
        is SDUIComponentDomain.Column -> toUi(json)
        is SDUIComponentDomain.Divider -> toUi(json)
        is SDUIComponentDomain.FloatingActionButton -> toUi(json)
        is SDUIComponentDomain.Icon -> toUi(json)
        is SDUIComponentDomain.IconButton -> toUi(json)
        is SDUIComponentDomain.Image -> toUi(json)
        is SDUIComponentDomain.LazyColumn -> TODO()
        is SDUIComponentDomain.LazyRow -> TODO()
        is SDUIComponentDomain.OutlinedButton -> toUi(json)
        is SDUIComponentDomain.Row -> toUi(json)
        is SDUIComponentDomain.Scaffold -> TODO()
        is SDUIComponentDomain.Spacer -> toUi(json)
        is SDUIComponentDomain.Surface -> toUi(json)
        is SDUIComponentDomain.Text -> toUi(json)
        is SDUIComponentDomain.TextField -> TODO()
        is SDUIComponentDomain.AvitoCheckBox -> toUi(json)
        is SDUIComponentDomain.AvitoNavBar -> toUi(json)
        is SDUIComponentDomain.AvitoSelectAll -> toUi(json)
        is SDUIComponentDomain.AvitoShopName -> toUi(json)
        is SDUIComponentDomain.AvitoCartItem -> toUi(json)
        is SDUIComponentDomain.RepetitiveComponent -> toUi(json)
    }
}

fun SDUIComponentDomain.Surface.toUi(json: JsonElement): SDUIComponent.Surface {
    return SDUIComponent.Surface(
        action = action.toUi(),
        children = children.map { it.toUi(json) },
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.Spacer.toUi(json: JsonElement): SDUIComponent.Spacer {
    return SDUIComponent.Spacer(
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.OutlinedButton.toUi(json: JsonElement): SDUIComponent.OutlinedButton {
    return SDUIComponent.OutlinedButton(
        action = action.toUi(),
        content = content.toUi(json),
        title = title,
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.Image.toUi(json: JsonElement): SDUIComponent.Image {
    return SDUIComponent.Image(
        action = action.toUi(),
        url = url.toUi(json),
        contentDescription = contentDescription,
        contentScale = contentScale?.toContentScale() ?: ContentScale.Fit,
        modifier = modifier.map { it.toUi() }
    )
}

fun String.toContentScale() = when (this) {
    "Crop" -> ContentScale.Crop
    "Fit" -> ContentScale.Fit
    "FillBounds" -> ContentScale.FillBounds
    "FillHeight" -> ContentScale.FillHeight
    "FillWidth" -> ContentScale.FillWidth
    "Inside" -> ContentScale.Inside
    "None" -> ContentScale.None
    else -> ContentScale.Fit
}

fun SDUIComponentDomain.FloatingActionButton.toUi(json: JsonElement): SDUIComponent.FloatingActionButton {
    return SDUIComponent.FloatingActionButton(
        action = action.toUi(),
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.Divider.toUi(json: JsonElement): SDUIComponent.Divider {
    return SDUIComponent.Divider(
        color = color?.toColor() ?: Color.LightGray,
        thickness = thickness?.dp ?: 1.dp,
        type = when (type) {
            SDUIComponentDomain.Divider.DividerType.HORIZONTAL -> SDUIComponent.Divider.DividerType.HORIZONTAL
            SDUIComponentDomain.Divider.DividerType.VERTICAL -> SDUIComponent.Divider.DividerType.VERTICAL
        },
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.Box.toUi(json: JsonElement): SDUIComponent.Box {
    return SDUIComponent.Box(
        action = action.toUi(),
        children = children.map { it.toUi(json) },
        contentAlignment = contentAlignment?.toAlignment() ?: Alignment.TopStart,
        modifier = modifier.map { it.toUi() }
    )
}

fun ModifierPropertiesDomain.toUi(): ModifierProperties {
    return when (this) {
        is ModifierPropertiesDomain.Alpha -> ModifierProperties.Alpha(alpha)
        is ModifierPropertiesDomain.Background -> ModifierProperties.Background(
            color.toUi(),
            shape.toCompose()
        )

        is ModifierPropertiesDomain.Border -> ModifierProperties.Border(
            width.dp,
            color.toColor(),
            shape.toCompose(),
        )

        is ModifierPropertiesDomain.Clip -> ModifierProperties.Clip(
            shape.toCompose()
        )

        is ModifierPropertiesDomain.FillMaxHeight -> ModifierProperties.FillMaxHeight(
            fraction = fraction
        )

        is ModifierPropertiesDomain.FillMaxSize -> ModifierProperties.FillMaxSize(
            fraction = fraction
        )

        is ModifierPropertiesDomain.FillMaxWidth -> ModifierProperties.FillMaxWidth(
            fraction = fraction
        )

        is ModifierPropertiesDomain.Height -> ModifierProperties.Height(
            value = value.dp
        )

        is ModifierPropertiesDomain.Padding -> ModifierProperties.Padding(
            PaddingProperties(
                start = value.start.dp,
                top = value.top.dp,
                end = value.end.dp,
                bottom = value.bottom.dp
            )
        )

        is ModifierPropertiesDomain.Shadow -> ModifierProperties.Shadow(
            elevation = elevation.dp,
            shape = shape.toCompose(),
            ambientColor = ambientColor?.toColor() ?: Color.Black,
            spotColor = spotColor?.toColor() ?: Color.Black,
        )

        is ModifierPropertiesDomain.Size -> ModifierProperties.Size(
            size = value.dp
        )

        is ModifierPropertiesDomain.Width -> ModifierProperties.Width(
            value = value.dp
        )

        is ModifierPropertiesDomain.WrapContentHeight -> ModifierProperties.WrapContentHeight(
            alignment = alignment.toAlignmentVertical()
        )

        is ModifierPropertiesDomain.WrapContentWidth -> ModifierProperties.WrapContentWidth(
            alignment = alignment.toAlignmentHorizontal()
        )

        is ModifierPropertiesDomain.Weight -> ModifierProperties.Weight(value = value)
        ModifierPropertiesDomain.MatchParentSize -> ModifierProperties.MatchParentSize
    }
}

fun SDUIComponentDomain.BottomBar.toUi(json: JsonElement): SDUIComponent.BottomBar {
    return SDUIComponent.BottomBar(
        action = action.toUi(),
        children = children.map { it.toUi(json) },
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.IconButton.toUi(json: JsonElement): SDUIComponent.IconButton {
    return SDUIComponent.IconButton(
        action = action.toUi(),
        modifier = modifier.map { it.toUi() },
        content = content.toUi(json)
    )
}

fun SDUIComponentDomain.Icon.toUi(json: JsonElement): SDUIComponent.Icon {
    return SDUIComponent.Icon(
        drawable = drawable.toUi(json),
        tint = tint.toUi(),
        modifier = modifier.map { it.toUi() },
        contentDescription = contentDescription
    )
}

fun ColorType.toUi(): ru.aleksandra.core.sdui.presentation.model.ColorType {
    return when (this) {
        is ColorType.StaticColor -> ru.aleksandra.core.sdui.presentation.model.ColorType.StaticColor(
            value.toColor()
        )

        is ColorType.ThemeColor -> ru.aleksandra.core.sdui.presentation.model.ColorType.ThemeColor(
            name
        )
    }
}

fun ru.aleksandra.core.sdui.domain.model.DrawableType.toUi(json: JsonElement): DrawableType {
    return when (this) {
        is ru.aleksandra.core.sdui.domain.model.DrawableType.DynamicDrawable -> DrawableType.DynamicDrawable(
            value = json.getByPath<String>(path, null) ?: error("No drawable found for path: $path")
        )

        is ru.aleksandra.core.sdui.domain.model.DrawableType.ThemeDrawable -> DrawableType.ThemeDrawable(
            resource = this.name.toDrawable() ?: error("No drawable found for name: ${this.name}")
        )

        is ru.aleksandra.core.sdui.domain.model.DrawableType.StaticDrawable -> DrawableType.StaticDrawable(
            value = value
        )
    }
}

fun SDUIComponentDomain.RepetitiveComponent.toUi(json: JsonElement): SDUIComponent.RepetitiveComponent {
    val list = mutableListOf<SDUIComponentDomain>()
    repeat(json.countByPath(itemsPath)) {
        list.add(content.updatePath(index = it, itemsPath = itemsPath))
    }
    return SDUIComponent.RepetitiveComponent(
        component = list.map { it.toUi(json) },
        modifier = modifier.map { it.toUi() }
    )
}

// TODO: Пока такое решение для классов, можно улучшить
fun SDUIComponentDomain.updatePath(index: Int, itemsPath: String): SDUIComponentDomain {
    return when (this) {
        is SDUIComponentDomain.Text -> {
            return this.copy(
                text = text.updatePathAndTransformation(index, itemsPath)
            )
        }

        is SDUIComponentDomain.Row -> {
            return this.copy(
                children = children.map { it.updatePath(index, itemsPath) }
            )
        }

        is SDUIComponentDomain.Column -> {
            return this.copy(
                children = children.map { it.updatePath(index, itemsPath) }
            )
        }

        is SDUIComponentDomain.Box -> {
            return this.copy(
                children = children.map { it.updatePath(index, itemsPath) }
            )
        }

        is SDUIComponentDomain.RepetitiveComponent -> {
            return this.copy(
                itemsPath = itemsPath.replace(
                    "*",
                    index.toString()
                ) + "." + this.itemsPath,
            )
        }

        is SDUIComponentDomain.Image -> {
            return this.copy(
                url = when (url) {
                    is ru.aleksandra.core.sdui.domain.model.DrawableType.DynamicDrawable -> {
                        url.copy(
                            itemsPath.replace(
                                "*",
                                index.toString()
                            ) + "." + this.url.path
                        )
                    }

                    is ru.aleksandra.core.sdui.domain.model.DrawableType.ThemeDrawable -> url
                    is ru.aleksandra.core.sdui.domain.model.DrawableType.StaticDrawable -> url
                }
            )
        }

        else -> this
    }
}

fun BindableString.updatePathAndTransformation(
    index: Int,
    itemsPath: String
): BindableString {
    return when (this) {
        is BindableValue.Dynamic -> {
            BindableValue.Dynamic(
                itemsPath.replace(
                    "*",
                    index.toString()
                ) + "." + path,
                transformation
            )
        }

        is BindableValue.Static -> {
            this
        }
    }
}

/*fun SDUIComponentDomain.AvitoCartItem.toUi(json: JsonElement): SDUIComponent.AvitoCartItem {
    return SDUIComponent.AvitoCartItem(
        isChecked = isChecked,
        name = name.toValue(json),
        priceWithDiscount = priceWithDiscount.toValue(json),
        priceWithoutDiscount = priceWithoutDiscount.toValue(json),
        salePercent = salePercent.toValue(json),
        count = count.toValue(json),
        imageUrl = imageUrl.toValue(json),
    )
}

fun SDUIComponentDomain.AvitoShopName.toUi(json: JsonElement): SDUIComponent.AvitoShopName {
    return SDUIComponent.AvitoShopName(
        isChecked = isChecked,
        shopName = shopName.toValue(json),
        rating = rating.toValue(json),
        reviewsCount = reviewsCount.toValue(json),
        action = action.toUi()
    )
}

fun SDUIComponentDomain.AvitoSelectAll.toUi(json: JsonElement): SDUIComponent.AvitoSelectAll {
    return SDUIComponent.AvitoSelectAll(
        isChecked = isChecked,
        deleteCount = deleteCount.toValue(json),
        action = action.toUi()
    )
}*/

inline fun <reified T> BindableValue<T>.toValue(json: JsonElement): T {

    return when (this) {
        is BindableValue.Static -> this.value
        is BindableValue.Dynamic -> {
            json.getByPath<T>(this.path, this.transformation)
                ?: error("No value found for path: ${this.path}")
        }
    }
}

/*fun SDUIComponentDomain.AvitoCheckBox.toUi(): SDUIComponent.AvitoCheckBox {
    return SDUIComponent.AvitoCheckBox(
        text = text,
        isChecked = isChecked,
        action = action.toUi()
    )
}

fun SDUIComponentDomain.AvitoNavBar.toUi(json: JsonElement): SDUIComponent.AvitoNavBar {
    return SDUIComponent.AvitoNavBar(
        title = title.toValue(json),
        action = action.toUi(),
    )
}*/

fun SDUIComponentDomain.Checkbox.toUi(json: JsonElement): SDUIComponent.Checkbox {
    return SDUIComponent.Checkbox(
        checked = checked,
        action = action.toUi(),
        enabled = enabled,
        colors = colors.toUi(),
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.Column.toUi(json: JsonElement): SDUIComponent.Column {
    return SDUIComponent.Column(
        action = action.toUi(),
        children = children.map { it.toUi(json) },
        verticalArrangement = verticalArrangement?.toArrangementVertical() ?: Arrangement.Top,
        horizontalAlignment = horizontalAlignment?.toAlignmentHorizontal() ?: Alignment.Start,
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.Row.toUi(json: JsonElement): SDUIComponent.Row {
    return SDUIComponent.Row(
        action = action.toUi(),
        children = children.map { it.toUi(json) },
        horizontalArrangement = horizontalArrangement?.toArrangementHorizontal()
            ?: Arrangement.Start,
        verticalAlignment = verticalAlignment?.toAlignmentVertical() ?: Alignment.Top,
        modifier = modifier.map { it.toUi() }
    )
}

fun SDUIComponentDomain.Text.toUi(json: JsonElement): SDUIComponent.Text {
    return SDUIComponent.Text(
        action = action.toUi(),
        text = text.toValue(json),
        style = style,
        color = color?.toUi(),
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
        modifier = modifier.map { it.toUi() },
    )
}

fun SDUIComponentDomain.Button.toUi(json: JsonElement): SDUIComponent.Button {
    return SDUIComponent.Button(
        action = action.toUi(),
        enabled = enabled,
        shape = shape?.toCompose(),
        colors = colors?.toCompose(),
        elevation = elevation?.toCompose(),
        border = border?.toCompose(),
        contentPadding = contentPadding?.toCompose(),
        content = content.toUi(json),
        modifier = modifier.map { it.toUi() }
    )
}

fun Action.toUi(): ActionUi =
    when (this) {
        is Action.Navigate -> ActionUi.Navigate(destination)
        Action.None -> ActionUi.None
        is Action.OpenUrl -> ActionUi.OpenUrl(url)
        is Action.ShowToast -> ActionUi.ShowToast(message)
        is Action.PopBack -> ActionUi.PopBack
    }

fun Shape.toCompose() = when (this) {
    is Shape.CircleShape -> CircleShape
    is Shape.RoundedCornerShape -> RoundedCornerShape(radius.dp)
    Shape.RectangleShape -> RectangleShape
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
    return when {
        this == "Center" -> Arrangement.Center
        this == "End" -> Arrangement.End
        this == "SpaceBetween" -> Arrangement.SpaceBetween
        this == "SpaceEvenly" -> Arrangement.SpaceEvenly
        this == "SpaceAround" -> Arrangement.SpaceAround
        this == "Start" -> Arrangement.Start
        this.startsWith("SpacedBy") -> {
            val value = this
                .removePrefix("SpacedBy(")
                .removeSuffix(")")
                .toFloatOrNull() ?: 0f
            Arrangement.spacedBy(value.dp)
        }

        else -> Arrangement.Start
    }
}

fun String.toArrangementVertical(): Arrangement.Vertical {
    return when {
        this == "Center" -> Arrangement.Center
        this == "SpaceBetween" -> Arrangement.SpaceBetween
        this == "SpaceEvenly" -> Arrangement.SpaceEvenly
        this == "SpaceAround" -> Arrangement.SpaceAround
        this == "Top" -> Arrangement.Top
        this == "Bottom" -> Arrangement.Bottom
        this.startsWith("SpacedBy") -> {
            val value = this
                .removePrefix("SpacedBy(")
                .removeSuffix(")")
                .toFloatOrNull() ?: 0f
            Arrangement.spacedBy(value.dp)
        }

        else -> Arrangement.Top
    }
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

