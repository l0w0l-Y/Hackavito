package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.foundation.layout.Arrangement
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ArrangementVerticalSerializer : KSerializer<Arrangement.Vertical> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ArrangementVertical", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Arrangement.Vertical) {
        val arrangement = when (value) {
            Arrangement.Top -> "Top"
            Arrangement.Bottom -> "Bottom"
            Arrangement.Center -> "Center"
            Arrangement.SpaceBetween -> "SpaceBetween"
            Arrangement.SpaceEvenly -> "SpaceEvenly"
            Arrangement.SpaceAround -> "SpaceAround"
            else -> "Top"
        }
        encoder.encodeString(arrangement)
    }

    override fun deserialize(decoder: Decoder): Arrangement.Vertical {
        val value = decoder.decodeString()
        return when (value) {
            "Center" -> Arrangement.Center
            "SpaceBetween" -> Arrangement.SpaceBetween
            "SpaceEvenly" -> Arrangement.SpaceEvenly
            "SpaceAround" -> Arrangement.SpaceAround
            "Top" -> Arrangement.Top
            "Bottom" -> Arrangement.Bottom
            else -> Arrangement.Top
        }
    }
}