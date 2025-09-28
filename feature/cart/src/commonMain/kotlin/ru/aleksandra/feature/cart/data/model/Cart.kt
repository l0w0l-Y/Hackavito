package ru.aleksandra.feature.cart.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val items: List<CartItem>,
    val count: Int,
    val totalPrice: Int,
)