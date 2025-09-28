package ru.aleksandra.feature.cart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.aleksandra.feature.cart.data.model.Cart
import ru.aleksandra.feature.cart.data.model.CartItem
import ru.aleksandra.feature.cart.data.model.Item
import ru.aleksandra.feature.cart.data.model.Store

class LoadCartRepositoryImpl() : LoadCartRepository {
    override suspend fun loadCart(): Flow<Cart> {
        return flowOf(
            Cart(
                items = listOf(
                    CartItem(
                        store = Store(
                            id = "1",
                            "Pear Store",
                            rating = 4.8f,
                            643
                        ),
                        items = listOf(
                            Item(
                                id = "1",
                                name = "Зарядка MagSafe Charger 15W 1 метр",
                                price = 4990,
                                discount = 5,
                                prevPrice = 9481,
                                count = 1
                            ),
                            Item(
                                id = "2",
                                name = "AirPods Pro 2",
                                price = 15191,
                                discount = 5,
                                prevPrice = 15990,
                                count = 1
                            )
                        )
                    )
                ),
                count = 0,
                totalPrice = 0
            )
        )
    }
}

interface LoadCartRepository {
    suspend fun loadCart(): Flow<Cart>
}