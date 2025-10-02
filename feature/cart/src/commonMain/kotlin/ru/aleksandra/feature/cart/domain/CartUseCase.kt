package ru.aleksandra.feature.cart.domain

import kotlinx.coroutines.flow.Flow
import ru.aleksandra.feature.cart.data.CartRepository
import ru.aleksandra.feature.cart.data.model.Cart

class CartUseCaseImpl(
    private val repository: CartRepository
) : CartUseCase {
    override suspend fun loadCart(): Flow<Cart> {
        return repository.loadCart()
    }
}

interface CartUseCase {
    suspend fun loadCart(): Flow<Cart>
}