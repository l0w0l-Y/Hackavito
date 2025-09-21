package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.style.TextOverflow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TextOverflowSerializer : KSerializer<TextOverflow> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TextOverflow", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TextOverflow {
        val value = decoder.decodeString()
        return when (value) {
            "Clip" -> TextOverflow.Clip
            "Ellipsis" -> TextOverflow.Ellipsis
            "Visible" -> TextOverflow.Visible
            "StartEllipsis" -> TextOverflow.StartEllipsis
            "MiddleEllipsis" -> TextOverflow.MiddleEllipsis
            else -> error("Неизвестный TextOverflow: $value")
        }
    }

    override fun serialize(encoder: Encoder, value: TextOverflow) {
        encoder.encodeString(value.toString())
    }
}