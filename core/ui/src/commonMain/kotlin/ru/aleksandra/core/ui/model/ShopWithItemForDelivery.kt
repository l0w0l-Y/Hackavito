package ru.aleksandra.core.ui.model

data class ShopWithItemForDelivery(
    val name: String,
    val item: Item,
    val address: String,
    val choosenDeliveryVariant: DeliveryVariant
)
