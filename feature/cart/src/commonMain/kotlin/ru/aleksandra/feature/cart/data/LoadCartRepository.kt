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
                                priceWithDiscount = 4990,
                                salePercent = 5,
                                priceWithoutDiscount = 9481,
                                count = 1,
                                imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                            ),
                            Item(
                                id = "2",
                                name = "AirPods Pro 2",
                                priceWithDiscount = 15191,
                                salePercent = 5,
                                priceWithoutDiscount = 15990,
                                count = 1,
                                imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                            )
                        )
                    ),
                    CartItem(
                        store = Store(
                            id = "2",
                            "TECHNO ZONE",
                            rating = 5.0f,
                            916
                        ),
                        items = listOf(
                            Item(
                                id = "1",
                                name = "Зарядка MagSafe Charger 15W 1 метр",
                                priceWithDiscount = 4990,
                                salePercent = 5,
                                priceWithoutDiscount = 9481,
                                count = 1,
                                imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                            ),
                            Item(
                                id = "2",
                                name = "AirPods Pro 2",
                                priceWithDiscount = 15191,
                                salePercent = 5,
                                priceWithoutDiscount = 15990,
                                count = 1,
                                imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                            )
                        )
                    )
                ),
                isSelected = false,
                totalItems = 3,
                totalPrice = 0
            )
        )
    }
}

interface LoadCartRepository {
    suspend fun loadCart(): Flow<Cart>
}