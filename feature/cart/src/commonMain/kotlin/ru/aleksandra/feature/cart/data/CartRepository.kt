package ru.aleksandra.feature.cart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.aleksandra.feature.cart.data.model.Cart

class CartRepositoryImpl(
    private val api: CartApi
) : CartRepository {
    override suspend fun loadCart(): Flow<Cart> {
        return flowOf(api.getCartItems())
    }
}

interface CartRepository {
    suspend fun loadCart(): Flow<Cart>
}