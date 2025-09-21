package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.Alignment
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AlignmentVerticalSerializer : KSerializer<Alignment.Vertical> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AlignmentVertical", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Alignment.Vertical) {
        val alignment = when (value) {
            Alignment.Top -> "Top"
            Alignment.CenterVertically -> "CenterVertically"
            Alignment.Bottom -> "Bottom"
            else -> "TopStart"
        }
        encoder.encodeString(alignment)
    }

    override fun deserialize(decoder: Decoder): Alignment.Vertical {
        val value = decoder.decodeString()
        return when (value) {
            "CenterVertically" -> Alignment.CenterVertically
            "Bottom" -> Alignment.Bottom
            "Top" -> Alignment.Top
            else -> Alignment.Bottom
        }
    }
}