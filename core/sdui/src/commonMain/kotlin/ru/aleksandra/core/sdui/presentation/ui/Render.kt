package ru.aleksandra.core.sdui.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.DividerProperties
import ru.aleksandra.core.sdui.presentation.model.ModifierProperties
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent

//TODO: Добавить обработку изображений для Image и Icon, Iconbutton и Floatingactionbutton

@Composable
fun Render(
    component: SDUIComponent,
    handleAction: (Action) -> Unit = {}
) = when (component) {
    is SDUIComponent.Box -> SDUIBox(component)
    is SDUIComponent.Button -> SDUIButton(component) { handleAction(component.action) }
    is SDUIComponent.Card -> SDUICard(component)
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
    is Nothing -> {}
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
    when (model.dividerProperties.type) {
        DividerProperties.DividerType.HORIZONTAL -> HorizontalDivider(
            modifier = buildModifier(model.modifier),
            thickness = model.dividerProperties.thickness.dp,
        )

        DividerProperties.DividerType.VERTICAL -> VerticalDivider(
            modifier = buildModifier(model.modifier),
            thickness = model.dividerProperties.thickness.dp,
        )
    }

}

@Composable
fun SDUIBox(model: SDUIComponent.Box) {
    Box {
        model.children.forEach { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUICard(model: SDUIComponent.Card) {
    Card {
        model.children.forEach { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUIColumn(model: SDUIComponent.Column) {
    Column {
        model.children.forEach { child ->
            Render(child)
        }
    }
}

@Composable
fun SDUIRow(model: SDUIComponent.Row, handleAction: (Action) -> Unit) {
    Row {
        model.children.forEach { child ->
            Render(child) { handleAction(child.action) }
        }
    }
}

@Composable
fun SDUIText(model: SDUIComponent.Text) {
    Text(
        text = model.value,
        modifier = buildModifier(model.modifier),
    )
}

@Composable
fun SDUIButton(model: SDUIComponent.Button, handleAction: () -> Unit) {
    Button(
        onClick = { handleAction() }
    ) {
        Text(
            text = model.title,
            modifier = buildModifier(model.modifier),
        )
    }
}

@Composable
fun SDUICheckbox(model: SDUIComponent.Checkbox, handleAction: () -> Unit) {
    Checkbox(
        checked = model.isChecked,
        onCheckedChange = { handleAction() }
    )

}

@Composable
fun buildModifier(modifierProperties: List<ModifierProperties>): Modifier {
    var modifier = Modifier.padding(0.dp)

    modifierProperties.forEach { property ->
        when (property) {
            is ModifierProperties.Clickable -> {
                modifier = modifier.clickable(property.value) {}
            }

            is ModifierProperties.Height -> {
                modifier = modifier.height(property.value.dp)
            }

            is ModifierProperties.Padding -> {
                modifier = modifier.padding(
                    start = property.value.start.dp,
                    top = property.value.top.dp,
                    end = property.value.end.dp,
                    bottom = property.value.bottom.dp
                )
            }

            is ModifierProperties.Width -> {
                modifier = modifier.width(property.value.dp)
            }
        }
    }

    return modifier
}