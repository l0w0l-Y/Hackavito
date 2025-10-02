package ru.aleksandra.core.ui.model

data class Delivery(
    val address: String,
    val deliveryVariant: List<DeliveryVariant>,
)
