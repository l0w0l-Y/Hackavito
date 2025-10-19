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

    override suspend fun selectStore(storeId: String): Flow<Cart> {
        return flowOf(api.selectStore(storeId))
    }

    override suspend fun selectAll(): Flow<Cart> {
        return flowOf(api.selectAll())
    }
}

interface CartRepository {
    suspend fun loadCart(): Flow<Cart>
    suspend fun selectStore(storeId: String): Flow<Cart>

    suspend fun selectAll(): Flow<Cart>
}