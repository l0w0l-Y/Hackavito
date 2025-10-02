package ru.aleksandra.core.ui.model

import androidx.compose.ui.graphics.Color
import io.ktor.util.internal.OpDescriptor

data class DeliveryVariant(
    val name: String,
    val price: Int,
    val priceWithoutDiscount: Int?,
    val fromDays: Int,
    val toDays: Int,
    val description: String,
    val descriptionColor: Color
)
