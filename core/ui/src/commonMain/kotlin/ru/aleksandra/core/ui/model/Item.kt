package ru.aleksandra.core.ui.model

data class Item(
    val name: String,
    val priceWithoutDiscount: Float,
    val priceWithDiscount: Float,
    val salePercent: Int,
    val count: Int,
    val image: String
)