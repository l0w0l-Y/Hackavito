package ru.aleksandra.feature.cart.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCase
import ru.aleksandra.core.sdui.presentation.SDUIViewModel
import ru.aleksandra.core.sdui.presentation.mapper.toUi
import ru.aleksandra.core.sdui.presentation.model.UIState
import ru.aleksandra.feature.cart.domain.CartUseCase

class CartViewModel(
    savedStateHandle: SavedStateHandle,
    val loadUIUseCase: LoadUIUseCase,
    val loadCartUseCase: CartUseCase,
) : SDUIViewModel(savedStateHandle) {

    var screen = MutableStateFlow<SDUIComponentDomain?>(null)

    init {
        viewModelScope.launch {
            loadScreen()
        }
    }

    suspend fun loadScreen() {
        _ui.value = UIState.Loading
        combine(
            loadUIUseCase.loadUI(screenId),
            loadCartUseCase.loadCart()
        ) { ui, cart ->
            screen.value = ui
            val json = Json.encodeToJsonElement(cart)
            Napier.d(json.toString())
            ui.toUi(json)
        }.collect {
            _ui.value = UIState.Loaded(it)
        }
    }

    fun selectStore(storeId: String) {
        viewModelScope.launch {
            loadCartUseCase.selectStore(storeId).collect { cart ->
                val json = Json.encodeToJsonElement(cart)
                _ui.value =
                    UIState.Loaded(screen.value?.toUi(json) ?: error("Screen is not loaded"))
            }
        }
    }

    fun selectAll() {
        viewModelScope.launch {
            loadCartUseCase.selectAll().collect { cart ->
                val json = Json.encodeToJsonElement(cart)
                _ui.value =
                    UIState.Loaded(screen.value?.toUi(json) ?: error("Screen is not loaded"))
            }
        }
    }
}