package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ColorSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Color", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Color) {
        val a = (value.alpha * 255).toInt().toString(16).padStart(2, '0')
        val r = (value.red * 255).toInt().toString(16).padStart(2, '0')
        val g = (value.green * 255).toInt().toString(16).padStart(2, '0')
        val b = (value.blue * 255).toInt().toString(16).padStart(2, '0')
        encoder.encodeString("#$a$r$g$b".uppercase())
    }

    override fun deserialize(decoder: Decoder): Color {
        val hex = decoder.decodeString().removePrefix("#")
        return when (hex.length) {
            6 -> { // RRGGBB
                val r = hex.substring(0, 2).toInt(16)
                val g = hex.substring(2, 4).toInt(16)
                val b = hex.substring(4, 6).toInt(16)
                Color(r, g, b)
            }

            8 -> { // AARRGGBB
                val a = hex.substring(0, 2).toInt(16)
                val r = hex.substring(2, 4).toInt(16)
                val g = hex.substring(4, 6).toInt(16)
                val b = hex.substring(6, 8).toInt(16)
                Color(r, g, b, a)
            }

            else -> error("Invalid color format: $hex")
        }
    }
}