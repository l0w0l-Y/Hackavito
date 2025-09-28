package ru.aleksandra.feature.cart.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val name: String,
    val price: Int,
    val discount: Int?,
    val prevPrice: Int?,
    val count: Int,
)