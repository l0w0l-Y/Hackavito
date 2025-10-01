package ru.aleksandra.core.sdui.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.ModifierProperties
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent
import ru.aleksandra.core.sdui.presentation.model.StyleProperties
import ru.aleksandra.core.ui.AvitoCartItem
import ru.aleksandra.core.ui.AvitoCheckbox
import ru.aleksandra.core.ui.AvitoNavBar
import ru.aleksandra.core.ui.AvitoSelectAll
import ru.aleksandra.core.ui.AvitoShopName

//TODO: Добавить обработку изображений для Image и Icon, Iconbutton и Floatingactionbutton

@Composable
fun Render(
    component: SDUIComponent,
    handleAction: (Action) -> Unit = {}
) = when (component) {
    is SDUIComponent.Box -> SDUIBox(component)
    is SDUIComponent.Button -> SDUIButton(component) { handleAction(component.action) }
    is SDUIComponent.Column -> SDUIColumn(component)
    is SDUIComponent.Divider -> SDUIDivider(component)
    is SDUIComponent.FloatingActionButton -> SDUIFloatingActionButton(component)
    is SDUIComponent.Icon -> SDUIIcon(component)
    is SDUIComponent.IconButton -> SDUIIconButton(component)
    is SDUIComponent.Image -> SDUIImage(component)
    is SDUIComponent.LazyColumn -> SDUILazyColumn(component)
    is SDUIComponent.LazyRow -> SDUILazyRow(component)
    is SDUIComponent.OutlinedButton -> SDUIOutlinedButton(component)
    is SDUIComponent.Row -> SDUIRow(component, handleAction)
    is SDUIComponent.Scaffold -> SDUIScaffold(component)
    is SDUIComponent.Spacer -> SDUISpacer(component)
    is SDUIComponent.Surface -> SDUISurface(component)
    is SDUIComponent.Text -> SDUIText(component)
    is SDUIComponent.TextField -> SDUITextField(component)
    is SDUIComponent.Checkbox -> SDUICheckbox(component) { handleAction(component.action) }
    is SDUIComponent.BottomBar -> SDUIBottomBar(component)
    is SDUIComponent.AvitoCheckBox -> AvitoCheckbox(component.isChecked)
    is SDUIComponent.AvitoNavBar -> AvitoNavBar(component.title)
    is SDUIComponent.AvitoSelectAll -> AvitoSelectAll(
        isChecked = component.isChecked,
        deleteCount = component.deleteCount
    )

    is SDUIComponent.AvitoShopName -> AvitoShopName(
        isChecked = component.isChecked,
        shopName = component.shopName,
        rating = component.rating,
        reviewsCount = component.reviewsCount,
        onCheckedChange = {}
    )

    is SDUIComponent.AvitoCartItem -> AvitoCartItem(
        isChecked = component.isChecked,
        name = component.name,
        priceWithoutDiscount = component.priceWithoutDiscount,
        priceWithDiscount = component.priceWithDiscount,
        salePercent = component.salePercent,
        count = component.count,
        image = component.imageUrl,
        onCheckedChange = {},
        onPlusItemCountClicked = {},
        onMinusItemCountClicked = {}
    )

    is SDUIComponent.RepetitiveComponent -> RepetitiveComponent(component) { handleAction(it) }
}

@Composable
fun RepetitiveComponent(model: SDUIComponent.RepetitiveComponent, handleAction: (Action) -> Unit) {
    model.component.forEach {
        Render(component = it, handleAction = handleAction)
    }
}

@Composable
fun SDUIBottomBar(model: SDUIComponent.BottomBar) {
    model.children.forEach {
        Render(it)
    }
}

@Composable
fun SDUITextField(model: SDUIComponent.TextField) {
    Text(
        text = "TextField: ${model.hint}",
        modifier = buildModifier(model.modifier)
    )
}

@Composable
fun SDUISurface(model: SDUIComponent.Surface) {
    Surface {
        model.children.forEach { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUISpacer(model: SDUIComponent.Spacer) {
    Spacer(modifier = buildModifier(model.modifier))
}

@Composable
fun SDUIScaffold(model: SDUIComponent.Scaffold) {
    Scaffold {
        Render(model.content)
    }
}

@Composable
fun SDUIOutlinedButton(model: SDUIComponent.OutlinedButton) {
    Button(
        onClick = { /* TODO: Handle button click, possibly using model.action */ }
    ) {
        Text(
            text = model.title,
            modifier = buildModifier(model.modifier),
        )
    }
}

@Composable
fun SDUILazyRow(model: SDUIComponent.LazyRow) {
    LazyRow {
        items(model.children) { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUILazyColumn(model: SDUIComponent.LazyColumn) {
    LazyColumn {
        items(model.children) { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUIImage(model: SDUIComponent.Image) {
    Text(
        model.url,
        modifier = buildModifier(model.modifier)
    )
}

@Composable
fun SDUIIconButton(model: SDUIComponent.IconButton) {
    IconButton({}) {
        Text(model.iconUrl)
    }
}

@Composable
fun SDUIIcon(model: SDUIComponent.Icon) {
    Text(model.url)
}

@Composable
fun SDUIFloatingActionButton(model: SDUIComponent.FloatingActionButton) {
    FloatingActionButton({}) {
        Text(model.iconUrl)
    }
}

@Composable
fun SDUIDivider(model: SDUIComponent.Divider) {
    when (model.type) {
        SDUIComponent.Divider.DividerType.HORIZONTAL -> HorizontalDivider(
            modifier = buildModifier(model.modifier),
            thickness = model.thickness ?: DividerDefaults.Thickness,
            color = model.color ?: DividerDefaults.color
        )

        SDUIComponent.Divider.DividerType.VERTICAL -> VerticalDivider(
            modifier = buildModifier(model.modifier),
            thickness = model.thickness ?: DividerDefaults.Thickness,
            color = model.color ?: DividerDefaults.color
        )
    }
}

@Composable
fun SDUIBox(model: SDUIComponent.Box) {
    Box(
        modifier = buildModifier(model.modifier),
        contentAlignment = model.contentAlignment,
        propagateMinConstraints = model.propagateMinConstraints,
    ) {
        model.children.forEach { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUIColumn(model: SDUIComponent.Column) {
    Column(
        modifier = buildModifier(model.modifier),
        verticalArrangement = model.verticalArrangement,
        horizontalAlignment = model.horizontalAlignment,
    ) {
        model.children.forEach { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUIRow(model: SDUIComponent.Row, handleAction: (Action) -> Unit) {
    Row(
        modifier = buildModifier(model.modifier),
        verticalAlignment = model.verticalAlignment,
        horizontalArrangement = model.horizontalArrangement,
    ) {
        model.children.forEach { child ->
            Render(child) { handleAction(child.action) }
        }
    }
}

@Composable
fun SDUIText(model: SDUIComponent.Text) {
    Text(
        text = model.text,
        modifier = buildModifier(model.modifier),
        color = model.color,
        fontSize = model.fontSize,
        fontStyle = model.fontStyle,
        fontWeight = model.fontWeight,
        fontFamily = model.fontFamily,
        letterSpacing = model.letterSpacing,
        lineHeight = model.lineHeight,
        textDecoration = model.textDecoration,
        textAlign = model.textAlign,
        maxLines = model.maxLines,
        minLines = model.minLines,
        overflow = model.overflow,
        softWrap = model.softWrap,
        //style = model.style?.toTextStyle() ?: LocalTextStyle.current,
    )
}

@Composable
fun StyleProperties.TextStyleProperties.toTextStyle(): TextStyle {
    return TextStyle(
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        background = background,
        textDecoration = textDecoration,
        shadow = shadow,
        textAlign = textAlign,
        textGeometricTransform = textGeometricTransform,
        localeList = localeList,
        baselineShift = baselineShift,
        textIndent = textIndent,
    )
}

@Composable
fun SDUIButton(model: SDUIComponent.Button, handleAction: () -> Unit) {
    Button(
        onClick = { handleAction() },
        modifier = buildModifier(model.modifier)
    ) {
        Render(model.content)
    }
}

@Composable
fun SDUICheckbox(model: SDUIComponent.Checkbox, handleAction: () -> Unit) {
    AvitoCheckbox(
        checked = model.checked,
        onCheckedChange = { handleAction() },
        modifier = buildModifier(model.modifier)
    )
}

@Composable
fun buildModifier(modifierProperties: List<ModifierProperties>): Modifier {
    var modifier = Modifier.padding(0.dp)

    modifierProperties.forEach { property ->
        when (property) {

            is ModifierProperties.Height -> {
                modifier = modifier.height(property.value)
            }

            is ModifierProperties.Padding -> {
                modifier = modifier.padding(
                    start = property.value.start,
                    top = property.value.top,
                    end = property.value.end,
                    bottom = property.value.bottom
                )
            }

            is ModifierProperties.Width -> {
                modifier = modifier.width(property.value)
            }

            is ModifierProperties.Alpha -> {
                modifier = modifier.alpha(property.alpha)
            }

            is ModifierProperties.Background -> {
                modifier = modifier.background(property.color)
            }

            is ModifierProperties.Border -> {
                modifier = modifier.border(property.width, property.color, property.shape)
            }

            is ModifierProperties.Clip -> {
                modifier = modifier.clip(property.shape)
            }

            is ModifierProperties.FillMaxHeight -> {
                modifier = modifier.fillMaxHeight(property.fraction)
            }

            is ModifierProperties.FillMaxSize -> {
                modifier = modifier.fillMaxSize(property.fraction)
            }

            is ModifierProperties.FillMaxWidth -> {
                modifier = modifier.fillMaxWidth(property.fraction)
            }

            is ModifierProperties.Shadow -> {
                modifier = modifier.shadow(
                    property.elevation,
                    property.shape,
                    property.clip,
                    property.ambientColor,
                    property.spotColor
                )
            }

            is ModifierProperties.Size -> {
                modifier = modifier.size(property.size)
            }

            is ModifierProperties.WrapContentHeight -> {
                modifier = modifier.wrapContentHeight(property.alignment)
            }

            is ModifierProperties.WrapContentWidth -> {
                modifier = modifier.wrapContentWidth(property.alignment)
            }
        }
    }

    return modifier
}