package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.style.TextDecoration
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TextDecorationSerializer : KSerializer<TextDecoration> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TextDecoration", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TextDecoration) {
        when (value) {
            TextDecoration.Underline -> encoder.encodeString("Underline")
            TextDecoration.LineThrough -> encoder.encodeString("LineThrough")
            else -> encoder.encodeString("None")
        }
    }

    override fun deserialize(decoder: Decoder): TextDecoration {
        val value = decoder.decodeString()
        return when (value) {
            "Underline" -> TextDecoration.Underline
            "LineThrough" -> TextDecoration.LineThrough
            else -> TextDecoration.None
        }
    }
}