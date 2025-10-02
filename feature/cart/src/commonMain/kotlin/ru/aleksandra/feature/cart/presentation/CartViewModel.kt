package ru.aleksandra.feature.cart.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCase
import ru.aleksandra.core.sdui.presentation.SDUIViewModel
import ru.aleksandra.core.sdui.presentation.mapper.toUi
import ru.aleksandra.core.sdui.presentation.model.UIState
import ru.aleksandra.feature.cart.domain.CartUseCase

class CartViewModel(
    savedStateHandle: SavedStateHandle,
    loadUIUseCase: LoadUIUseCase,
    loadCartUseCase: CartUseCase,
) : SDUIViewModel(savedStateHandle) {

    init {
        viewModelScope.launch {
            _ui.value = UIState.Loading
            combine(
                loadUIUseCase.loadUI(screenId),
                loadCartUseCase.loadCart()
            ) { ui, cart ->
                val json = Json.encodeToJsonElement(cart)
                ui.toUi(json)
            }.collect {
                _ui.value = UIState.Loaded(it)
            }
        }
    }
}