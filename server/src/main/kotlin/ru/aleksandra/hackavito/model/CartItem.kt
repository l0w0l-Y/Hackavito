package ru.aleksandra.hackavito.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val store: Store,
    val items: List<Item>,
    val isSelected: Boolean,
)