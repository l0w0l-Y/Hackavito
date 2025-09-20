package ru.aleksandra.core.sdui

import androidx.compose.ui.text.TextStyle

sealed class SDUIComponent(
    open val modifier: ModifierProperties? = null
) {
    // Text components
    data class Text(
        override val modifier: ModifierProperties?,
        val value: String,
        val style: TextStyle
    ) : SDUIComponent()

    data class TextField(
        override val modifier: ModifierProperties?,
        val hint: String,
        val value: String,
    ) : SDUIComponent()

    // Button components
    data class Button(
        override val modifier: ModifierProperties?,
        val title: String,
        val action: Action
    ) : SDUIComponent()

    data class OutlinedButton(
        override val modifier: ModifierProperties?,
        val title: String,
        val action: Action
    ) : SDUIComponent()

    data class IconButton(
        override val modifier: ModifierProperties?,
        val iconUrl: String,
        val action: Action
    ) : SDUIComponent()

    data class FloatingActionButton(
        override val modifier: ModifierProperties?,
        val iconUrl: String,
        val action: Action
    ) : SDUIComponent()

    // Layout components
    data class Column(
        override val modifier: ModifierProperties?,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class Row(override val modifier: ModifierProperties?, val children: List<SDUIComponent>) :
        SDUIComponent()

    data class Box(override val modifier: ModifierProperties?, val children: List<SDUIComponent>) :
        SDUIComponent()

    data class LazyColumn(
        override val modifier: ModifierProperties?,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class LazyRow(
        override val modifier: ModifierProperties?,
        val children: List<SDUIComponent>
    ) : SDUIComponent()

    data class Scaffold(
        override val modifier: ModifierProperties?,
        val topBar: SDUIComponent?,
        val content: SDUIComponent
    ) : SDUIComponent()

    // Utility components
    data class Spacer(override val modifier: ModifierProperties?, val height: Int) : SDUIComponent()
    data class Divider(
        override val modifier: ModifierProperties?,
        val thickness: Int,
        val dividerProperties: DividerProperties
    ) : SDUIComponent()

    // Media components
    data class Image(override val modifier: ModifierProperties?, val url: String) : SDUIComponent()
    data class Icon(override val modifier: ModifierProperties?, val url: String) : SDUIComponent()

    // Containers
    data class Card(override val modifier: ModifierProperties?, val children: List<SDUIComponent>) :
        SDUIComponent()

    data class Surface(
        override val modifier: ModifierProperties?,
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

data class ModifierProperties(
    val width: Int? = null,
    val height: Int? = null,
    val padding: Padding? = null,
    val clickable: Boolean? = null,
)

data class Padding(
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

sealed class Modifier {
    data class Padding(
        val start: Int,
        val top: Int,
        val end: Int,
        val bottom: Int
    ) : Modifier()

    data class Width(val value: Int) : Modifier()
}