package ru.aleksandra.core.sdui.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.ButtonColors
import ru.aleksandra.core.sdui.presentation.model.ColorType
import ru.aleksandra.core.sdui.presentation.model.DrawableType
import ru.aleksandra.core.sdui.presentation.model.ModifierProperties
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent
import ru.aleksandra.core.sdui.presentation.model.StyleProperties
import ru.aleksandra.core.theme.toTextStyle
import ru.aleksandra.core.theme.toThemeColor
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
    is SDUIComponent.Box -> SDUIBox(component, handleAction)
    is SDUIComponent.Button -> SDUIButton(component) { handleAction(component.action) }
    is SDUIComponent.Column -> SDUIColumn(component, handleAction)
    is SDUIComponent.Divider -> SDUIDivider(component)
    is SDUIComponent.FloatingActionButton -> SDUIFloatingActionButton(component)
    is SDUIComponent.Icon -> SDUIIcon(component)
    is SDUIComponent.IconButton -> SDUIIconButton(component) { handleAction(component.action) }
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
    Surface(
        buildModifier(model.modifier),
    ) {
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
    Scaffold(
        buildModifier(model.modifier),
    ) {
        Render(model.content)
    }
}

@Composable
fun SDUIOutlinedButton(model: SDUIComponent.OutlinedButton) {
    Button(
        onClick = { /* TODO: Handle button click, possibly using model.action */ },
        buildModifier(model.modifier),
    ) {
        Text(
            text = model.title,
            modifier = buildModifier(model.modifier),
        )
    }
}

@Composable
fun SDUILazyRow(model: SDUIComponent.LazyRow) {
    LazyRow(
        buildModifier(model.modifier),
    ) {
        items(model.children) { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUILazyColumn(model: SDUIComponent.LazyColumn) {
    LazyColumn(
        buildModifier(model.modifier),
    ) {
        items(model.children) { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUIImage(model: SDUIComponent.Image) {
    model.toCompose()
}

@Composable
fun SDUIComponent.Image.toCompose() {
    //TODO: Вернуть общий элемент
    when (this.url) {
        is DrawableType.DynamicDrawable -> {
            AsyncImage(
                url.value,
                contentDescription = this.contentDescription,
                modifier = buildModifier(this.modifier),
                contentScale = this.contentScale
            )
        }

        is DrawableType.StaticDrawable -> {
            AsyncImage(
                url.value,
                contentDescription = this.contentDescription,
                modifier = buildModifier(this.modifier),
                contentScale = this.contentScale
            )
        }

        is DrawableType.ThemeDrawable -> {
            androidx.compose.foundation.Image(
                painter = painterResource(url.resource),
                contentDescription = this.contentDescription,
                modifier = buildModifier(this.modifier),
                contentScale = this.contentScale
            )
        }
    }
}

sealed class MyScope {
    data class RowScope(val scope: androidx.compose.foundation.layout.RowScope) : MyScope()
    data class ColumnScope(val scope: androidx.compose.foundation.layout.ColumnScope) : MyScope()
    data class BoxScope(val scope: androidx.compose.foundation.layout.BoxScope) : MyScope()
    data object None : MyScope()
}

@Composable
fun SDUIIconButton(model: SDUIComponent.IconButton, handleAction: (Action) -> Unit) {
    IconButton(
        {
            handleAction(model.action)
        },
        buildModifier(model.modifier),
    ) {
        Render(model.content)
    }
}

@Composable
fun SDUIIcon(model: SDUIComponent.Icon) {
    when (model.drawable) {
        is DrawableType.ThemeDrawable -> {
            Icon(
                painterResource(model.drawable.resource),
                contentDescription = model.contentDescription,
                modifier = buildModifier(model.modifier),
                tint = model.tint.toCompose(),
            )
        }

        else -> {}
    }
}

@Composable
fun ColorType.toCompose(): Color {
    return when (this) {
        is ColorType.StaticColor -> this.value
        is ColorType.ThemeColor -> this.name.toThemeColor()
    }
}

@Composable
fun SDUIFloatingActionButton(model: SDUIComponent.FloatingActionButton) {
    FloatingActionButton({}) {
        //
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
fun SDUIBox(model: SDUIComponent.Box, handleAction: (Action) -> Unit) {
    Box(
        modifier = buildModifier(model.modifier, handleAction),
        contentAlignment = model.contentAlignment,
        propagateMinConstraints = model.propagateMinConstraints,
    ) {
        CompositionLocalProvider(LocalScopeData provides MyScope.BoxScope(this)) {
            model.children.forEach { child ->
                Render(child, handleAction)
            }
        }
    }
}

@Composable
fun SDUIColumn(model: SDUIComponent.Column, handleAction: (Action) -> Unit) {
    Column(
        modifier = buildModifier(model.modifier),
        verticalArrangement = model.verticalArrangement,
        horizontalAlignment = model.horizontalAlignment,
    ) {
        CompositionLocalProvider(LocalScopeData provides MyScope.ColumnScope(this)) {
            model.children.forEach { child ->
                Render(child, handleAction)
            }
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
        CompositionLocalProvider(LocalScopeData provides MyScope.RowScope(this)) {
            model.children.forEach { child ->
                Render(child, handleAction)
            }
        }
    }
}

@Composable
fun SDUIText(model: SDUIComponent.Text) {
    Text(
        text = model.text,
        modifier = buildModifier(model.modifier),
        color = model.color?.toCompose() ?: Color.Unspecified,
        style = model.style?.toTextStyle() ?: LocalTextStyle.current,
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
        shape = model.shape ?: ButtonDefaults.shape,
        colors = model.colors?.toCompose() ?: ButtonDefaults.buttonColors(),
        contentPadding = model.contentPadding ?: ButtonDefaults.ContentPadding,
        modifier = buildModifier(model.modifier)
    ) {
        Render(model.content)
    }
}

@Composable
fun ButtonColors.toCompose(): androidx.compose.material3.ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = this.containerColor?.toCompose() ?: Color.Unspecified,
        contentColor = this.contentColor?.toCompose() ?: Color.Unspecified,
        disabledContainerColor = this.disabledContainerColor?.toCompose() ?: Color.Unspecified,
        disabledContentColor = this.disabledContentColor?.toCompose() ?: Color.Unspecified
    )
}

@Composable
fun SDUICheckbox(model: SDUIComponent.Checkbox, handleAction: () -> Unit) {
    AvitoCheckbox(
        checked = model.checked,
        onCheckedChange = { handleAction() },
        modifier = buildModifier(model.modifier)
    )
}

val LocalScopeData = compositionLocalOf<MyScope> { MyScope.None }

@Composable
fun buildModifier(
    modifierProperties: List<ModifierProperties>,
    handleAction: (Action) -> Unit = {}
): Modifier {
    var modifier: Modifier = Modifier

    modifierProperties.forEach { property ->
        modifier =
            when (property) {

                is ModifierProperties.Height -> {
                    modifier.then(Modifier.height(property.value))
                }

                is ModifierProperties.Padding -> {
                    modifier.then(
                        Modifier.padding(
                            start = property.value.start,
                            top = property.value.top,
                            end = property.value.end,
                            bottom = property.value.bottom
                        )
                    )
                }

                is ModifierProperties.Width -> {
                    modifier.then(Modifier.width(property.value))
                }

                is ModifierProperties.Alpha -> {
                    modifier.then(Modifier.alpha(property.alpha))
                }

                is ModifierProperties.Background -> {
                    modifier.then(Modifier.background(property.color.toCompose(), property.shape))
                }

                is ModifierProperties.Border -> {
                    modifier.then(Modifier.border(property.width, property.color, property.shape))
                }

                is ModifierProperties.Clip -> {
                    modifier.then(Modifier.clip(property.shape))
                }

                is ModifierProperties.FillMaxHeight -> {
                    modifier.then(Modifier.fillMaxHeight(property.fraction))
                }

                is ModifierProperties.FillMaxSize -> {
                    modifier.then(Modifier.fillMaxSize(property.fraction))
                }

                is ModifierProperties.FillMaxWidth -> {
                    modifier.then(Modifier.fillMaxWidth(property.fraction))
                }

                is ModifierProperties.Shadow -> {
                    modifier
                }

                is ModifierProperties.Size -> {
                    modifier.then(Modifier.size(property.size))
                }

                is ModifierProperties.WrapContentHeight -> {
                    modifier.then(Modifier.wrapContentHeight(property.alignment))
                }

                is ModifierProperties.WrapContentWidth -> {
                    modifier.then(Modifier.wrapContentWidth(property.alignment))
                }

                is ModifierProperties.MatchParentSize -> {
                    if (LocalScopeData.current is MyScope.BoxScope) {
                        with((LocalScopeData.current as MyScope.BoxScope).scope) {
                            modifier.then(Modifier.matchParentSize())
                        }
                    } else modifier
                }

                is ModifierProperties.Weight -> {
                    when (LocalScopeData.current) {
                        is MyScope.RowScope -> {
                            with((LocalScopeData.current as MyScope.RowScope).scope) {
                                modifier.then(Modifier.weight(property.value))
                            }
                        }

                        is MyScope.ColumnScope -> {
                            with((LocalScopeData.current as MyScope.ColumnScope).scope) {
                                modifier.then(Modifier.weight(property.value))
                            }
                        }

                        else -> modifier
                    }
                }

                is ModifierProperties.Align -> {
                    when (LocalScopeData.current) {

                        is MyScope.BoxScope -> {
                            with((LocalScopeData.current as MyScope.BoxScope).scope) {
                                modifier.then(Modifier.align(property.alignment))
                            }
                        }

                        else -> modifier
                    }
                }

                is ModifierProperties.Clickable -> {
                    modifier.then(
                        Modifier.clickable {
                            handleAction(property.action)
                        }
                    )
                }
            }
    }

    return modifier
}