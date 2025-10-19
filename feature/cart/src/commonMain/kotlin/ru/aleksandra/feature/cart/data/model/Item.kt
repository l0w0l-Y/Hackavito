package ru.aleksandra.feature.cart.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val name: String,
    val priceWithoutDiscount: Int,
    val salePercent: Int?,
    val priceWithDiscount: Int?,
    val count: Int,
    val imageUrl: String,
    val isSelected: Boolean,
)