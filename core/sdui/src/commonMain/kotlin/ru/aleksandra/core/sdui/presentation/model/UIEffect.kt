package ru.aleksandra.core.sdui.presentation.model

sealed class UIEffect {
    data class NavigateEffect(val destination: String) : UIEffect()
    data class OpenUrlEffect(val url: String) : UIEffect()
}