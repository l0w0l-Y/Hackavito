package ru.aleksandra.core.sdui.presentation.model

sealed class UIEffect {
    data object PopBackEffect : UIEffect()
    data class NavigateEffect(val destination: String) : UIEffect()
    data class OpenUrlEffect(val url: String) : UIEffect()
    data class CustomEffect(val name: String) : UIEffect()
}