package ru.aleksandra.core.sdui.presentation.model

sealed class UIState {
    data object Init : UIState()
    data object Loading : UIState()
    data class Error(val message: String) : UIState()
    data class Loaded(val ui: SDUIComponent) : UIState()
}