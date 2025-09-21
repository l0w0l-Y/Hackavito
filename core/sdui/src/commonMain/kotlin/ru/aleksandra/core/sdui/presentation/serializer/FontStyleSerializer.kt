package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.font.FontStyle
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object FontStyleSerializer : KSerializer<FontStyle> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontStyle", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): FontStyle {
        val value = decoder.decodeString()
        return when (value) {
            "Italic" -> FontStyle.Italic
            else -> FontStyle.Normal
        }
    }

    override fun serialize(encoder: Encoder, value: FontStyle) {
        val fontStyle = when (value) {
            FontStyle.Italic -> "Italic"
            else -> "Normal"
        }
        encoder.encodeString(fontStyle)
    }
}