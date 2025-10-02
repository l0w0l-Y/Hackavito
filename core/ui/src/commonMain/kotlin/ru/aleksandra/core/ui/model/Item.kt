package ru.aleksandra.core.ui.model

import org.jetbrains.compose.resources.DrawableResource

data class Item(
    val name: String,
    val priceWithoutDiscount: Float,
    val priceWithDiscount: Float,
    val salePercent: Int,
    val count: Int,
    val image: DrawableResource
)