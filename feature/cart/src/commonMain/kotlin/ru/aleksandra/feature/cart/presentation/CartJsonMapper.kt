package ru.aleksandra.feature.cart.presentation

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.longOrNull
import ru.aleksandra.feature.cart.data.model.Cart
import ru.aleksandra.feature.cart.data.model.CartItem
import ru.aleksandra.feature.cart.data.model.Item
import ru.aleksandra.feature.cart.data.model.Store

fun Cart.toMap(): Map<String, Any?> =
    mapOf(
        "totalPrice" to totalPrice,
        "items" to items.map { it.toMap() }
    )

fun Item.toMap(): Map<String, Any?> =
    mapOf(
        "id" to id,
        "name" to name,
        "count" to count
    )

fun CartItem.toMap(): Map<String, Any?> =
    mapOf(
        "store" to store.toMap(),
        "items" to items.map { it.toMap() }
    )

fun Store.toMap(): Map<String, Any?> =
    mapOf(
        "id" to id,
        "name" to name,
        "rating" to rating,
        "reviewsCount" to reviewsCount,
    )