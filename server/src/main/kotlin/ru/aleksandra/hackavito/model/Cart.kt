package ru.aleksandra.hackavito.model

import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val items: List<CartItem>,
    val totalItems: Int,
    val isSelected: Boolean = false,
    val totalPrice: Int = items.sumOf { it.items.sumOf { it.priceWithoutDiscount * it.count } },
)