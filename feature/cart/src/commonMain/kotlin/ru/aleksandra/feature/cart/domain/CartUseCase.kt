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

    override suspend fun selectStore(storeId: String): Flow<Cart> {
        return repository.selectStore(storeId)
    }

    override suspend fun selectAll(): Flow<Cart> {
        return repository.selectAll()
    }
}

interface CartUseCase {
    suspend fun loadCart(): Flow<Cart>
    suspend fun selectStore(storeId: String): Flow<Cart>
    suspend fun selectAll(): Flow<Cart>
}