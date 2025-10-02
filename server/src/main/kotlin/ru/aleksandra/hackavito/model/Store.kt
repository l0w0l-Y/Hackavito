package ru.aleksandra.hackavito.model

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: String,
    val name: String,
    val rating: Float,
    val reviewsCount: Int,
)