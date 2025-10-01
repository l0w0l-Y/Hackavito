package ru.aleksandra.core.sdui.presentation.mapper

import io.github.aakira.napier.Napier
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.longOrNull
import ru.aleksandra.core.sdui.domain.model.Transformation

inline fun <reified T> JsonElement.getByPath(path: String, transformation: Transformation?): T? {
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
        String::class -> {
            when (transformation) {
                is Transformation.Format -> {
                    val content = primitive.content
                    transformation.pattern.replace("%s", content) as T
                }

                null -> primitive.content as T
            }
        }

        Int::class -> primitive.intOrNull as T?
        Double::class -> primitive.doubleOrNull as T?
        Float::class -> primitive.floatOrNull as T?
        Long::class -> primitive.longOrNull as T?
        Boolean::class -> primitive.booleanOrNull as T?
        else -> {
            Napier.d("Unsupported type: ${T::class}")
            null
        }
    }
}

/*inline fun <reified T> JsonElement.getByPathAll(path: String): List<T?> {
    fun extract(element: JsonElement, parts: List<String>): List<JsonElement> {
        if (parts.isEmpty()) return listOf(element)

        val key = parts.first()
        val rest = parts.drop(1)

        return when (element) {
            is JsonObject -> element[key]?.let { extract(it, rest) } ?: emptyList()
            is JsonArray -> {
                if (key == "*") {
                    element.flatMap { extract(it, rest) }
                } else {
                    val idx = key.toIntOrNull() ?: return emptyList()
                    element.getOrNull(idx)?.let { extract(it, rest) } ?: emptyList()
                }
            }

            else -> emptyList()
        }
    }

    return extract(this, path.split("."))
        .mapNotNull { it as? JsonPrimitive }
        .map { primitive ->
            when (T::class) {
                String::class -> primitive.content as T
                Int::class -> primitive.intOrNull as T?
                Double::class -> primitive.doubleOrNull as T?
                Float::class -> primitive.floatOrNull as T?
                Long::class -> primitive.longOrNull as T?
                Boolean::class -> primitive.booleanOrNull as T?
                else -> null
            }
        }
}*/
/*
inline fun <reified T> buildClass(json: JsonElement, namePath: String): List<T?> {
    return json.getByPathAll<T>(namePath)
}*/

fun JsonElement.countByPath(path: String): Int {
    fun extract(element: JsonElement, parts: List<String>): List<JsonElement> {
        if (parts.isEmpty()) return listOf(element)

        val key = parts.first()
        val rest = parts.drop(1)

        return when (element) {
            is JsonObject -> element[key]?.let { extract(it, rest) } ?: emptyList()
            is JsonArray -> {
                if (key == "*") {
                    element.flatMap { extract(it, rest) }
                } else {
                    val idx = key.toIntOrNull() ?: return emptyList()
                    element.getOrNull(idx)?.let { extract(it, rest) } ?: emptyList()
                }
            }

            else -> emptyList()
        }
    }

    return extract(this, path.split(".")) // достаём все элементы
        .size
}