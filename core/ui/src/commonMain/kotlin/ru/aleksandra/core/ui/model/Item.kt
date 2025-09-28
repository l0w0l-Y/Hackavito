package ru.aleksandra.core.ui.model

import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.img
import org.jetbrains.compose.resources.DrawableResource

data class Item(
    val name: String,
    val priceWithoutDiscount: Float,
    val priceWithDiscount: Float,
    val salePercent: Int,
    val image: DrawableResource = Res.drawable.img
    )