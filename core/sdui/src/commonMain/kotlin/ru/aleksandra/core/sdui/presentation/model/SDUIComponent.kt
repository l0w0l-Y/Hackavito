package ru.aleksandra.core.sdui.presentation.model

import androidx.compose.ui.text.TextStyle

sealed class SDUIComponent(
    open val modifier: List<ModifierProperties> = emptyList(),
    open val action: Action = Action.None
) {
    // Text components
    data class Text(
        override val modifier: List<ModifierProperties>,
        val value: String,
        val style: TextStyle
    ) : SDUIComponent()

    data class TextField(
        override val modifier: List<ModifierProperties>,
        val hint: String,
        val value: String,
    ) : SDUIComponent()

    // Button components
    data class Button(
        override val modifier: List<ModifierProperties>,
        val title: String,
        override val action: Action
    ) : SDUIComponent()

    data class OutlinedButton(
        override val modifier: List<ModifierProperties>,
        val title: String,
        override val action: Action
    ) : SDUIComponent()

    data class IconButton(
        override val modifier: List<ModifierProperties>,
        val iconUrl: String,
        override val action: Action
    ) : SDUIComponent()

    data class FloatingActionButton(
        override val modifier: List<ModifierProperties>,
        val iconUrl: String,
        override val action: Action
    ) : SDUIComponent()

    // Layout components
    data class Column(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class Row(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) :
        SDUIComponent()

    data class Box(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) :
        SDUIComponent()

    data class LazyColumn(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class LazyRow(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class Scaffold(
        override val modifier: List<ModifierProperties>,
        val topBar: SDUIComponent?,
        val content: SDUIComponent
    ) : SDUIComponent()

    // Utility components
    data class Spacer(override val modifier: List<ModifierProperties>, val height: Int) :
        SDUIComponent()

    data class Divider(
        override val modifier: List<ModifierProperties>,
        val thickness: Int,
        val dividerProperties: DividerProperties
    ) : SDUIComponent()

    // Media components
    data class Image(override val modifier: List<ModifierProperties>, val url: String) :
        SDUIComponent()

    data class Icon(override val modifier: List<ModifierProperties>, val url: String) :
        SDUIComponent()

    // Containers
    data class Card(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) :
        SDUIComponent()

    data class Surface(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class Checkbox(
        override val modifier: List<ModifierProperties>,
        val isChecked: Boolean,
        val label: String,
        override val action: Action
    ) : SDUIComponent()

    data class BottomBar(
        override val modifier: List<ModifierProperties>,
        val children: List<SDUIComponent>
    ) : SDUIComponent()
}

data class DividerProperties(
    val thickness: Int,
    val color: String? = null,
    val type: DividerType
) {
    enum class DividerType {
        HORIZONTAL, VERTICAL
    }
}

sealed class ModifierProperties {
    data class Width(val value: Int) : ModifierProperties()
    data class Height(val value: Int) : ModifierProperties()
    data class Padding(val value: PaddingProperties) : ModifierProperties()
    data class Clickable(val value: Boolean) : ModifierProperties()
}

data class PaddingProperties(
    val start: Int,
    val top: Int,
    val end: Int,
    val bottom: Int
) {
    constructor(all: Int) : this(all, all, all, all)
    constructor(horizontal: Int, vertical: Int) : this(horizontal, vertical, horizontal, vertical)
}

sealed class Action {
    data class OpenUrl(val url: String) : Action()
    data class ShowToast(val message: String) : Action()
    data class Navigate(val destination: String) : Action()
    data object None : Action()
}

sealed class StyleProperties {
    data class TextStyleProperties(
        val fontSize: Int? = null,
        val color: String? = null,
        val bold: Boolean? = null,
        val italic: Boolean? = null,
        val underline: Boolean? = null
    ) : StyleProperties()

    data class ButtonStyleProperties(
        val backgroundColor: String? = null,
        val contentColor: String? = null,
        val disabledBackgroundColor: String? = null,
        val disabledContentColor: String? = null
    ) : StyleProperties()
}