package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.style.TextDirection
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TextDirectionSerializer : KSerializer<TextDirection> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TextDirection", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TextDirection {
        val value = decoder.decodeString()
        return when (value) {
            "Ltr" -> TextDirection.Ltr
            "Rtl" -> TextDirection.Rtl
            "Content" -> TextDirection.Content
            "ContentOrLtr" -> TextDirection.ContentOrLtr
            "ContentOrRtl" -> TextDirection.ContentOrRtl
            else -> TextDirection.Unspecified
        }
    }

    override fun serialize(encoder: Encoder, value: TextDirection) {
        val baselineShift = when (value) {
            TextDirection.Ltr -> "Ltr"
            TextDirection.Rtl -> "Rtl"
            TextDirection.Content -> "Content"
            TextDirection.ContentOrLtr -> "ContentOrLtr"
            TextDirection.ContentOrRtl -> "ContentOrRtl"
            else -> "Unspecified"
        }
        encoder.encodeString(baselineShift)
    }
}