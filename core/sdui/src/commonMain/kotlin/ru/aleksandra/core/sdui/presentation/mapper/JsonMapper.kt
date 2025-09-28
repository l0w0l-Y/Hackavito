package ru.aleksandra.core.sdui.presentation.mapper

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.longOrNull

inline fun <reified T> JsonElement.getByPath(path: String): T? {
    var current: JsonElement = this
    for (key in path.split(".")) {
        current = when (current) {
            is JsonObject -> current[key] ?: return null
            is JsonArray -> {
                val index = key.toIntOrNull() ?: return null
                current.getOrNull(index) ?: return null
            }

            else -> return null
        }
    }

    val primitive = current as? JsonPrimitive ?: return null

    return when (T::class) {
        String::class -> primitive.content as T
        Int::class -> primitive.intOrNull as T?
        Double::class -> primitive.doubleOrNull as T?
        Float::class -> primitive.floatOrNull as T?
        Long::class -> primitive.longOrNull as T?
        Boolean::class -> primitive.booleanOrNull as T?
        else -> null
    }
}