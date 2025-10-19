package ru.aleksandra.core.ui.model

data class Item(
    val name: String,
    val priceWithoutDiscount: Int,
    val priceWithDiscount: Int,
    val salePercent: Int,
    val count: Int,
    val image: String
)