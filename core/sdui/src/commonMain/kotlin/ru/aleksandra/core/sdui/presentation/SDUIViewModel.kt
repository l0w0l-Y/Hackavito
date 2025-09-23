package ru.aleksandra.core.sdui.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCase
import ru.aleksandra.core.sdui.presentation.mapper.toUi
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.UIEffect
import ru.aleksandra.core.sdui.presentation.model.UIState

class SDUIViewModel(
    savedStateHandle: SavedStateHandle,
    loadUIUseCase: LoadUIUseCase
) : ViewModel() {

    private val screenId = savedStateHandle.toRoute<NavigationDestination.SDUIScreen>().screenId
    private var _ui = MutableStateFlow<UIState>(UIState.Init)
    val ui: StateFlow<UIState> = _ui

    private val _sideEffects: MutableSharedFlow<UIEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<UIEffect> = _sideEffects

    init {
        viewModelScope.launch {
            _ui.value = UIState.Loading
            loadUIUseCase.loadUI(screenId).collect {
                _ui.value = UIState.Loaded(it.toUi())
            }
        }
    }

    fun handleAction(action: Action) {
        when (action) {
            is Action.Navigate -> {
                viewModelScope.launch {
                    _sideEffects.emit(UIEffect.NavigateEffect(action.destination))
                }
            }

            is Action.OpenUrl -> {
                viewModelScope.launch {
                    _sideEffects.emit(UIEffect.OpenUrlEffect(action.url))
                }
            }

            else -> {

            }
        }
    }
}