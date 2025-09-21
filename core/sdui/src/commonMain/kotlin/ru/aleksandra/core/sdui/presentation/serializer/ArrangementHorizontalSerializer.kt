package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.foundation.layout.Arrangement
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ArrangementHorizontalSerializer : KSerializer<Arrangement.Horizontal> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AlignmentHorizontal", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Arrangement.Horizontal) {
        val arrangement = when (value) {
            Arrangement.Start -> "Start"
            Arrangement.End -> "End"
            Arrangement.Center -> "Center"
            Arrangement.SpaceBetween -> "SpaceBetween"
            Arrangement.SpaceEvenly -> "SpaceEvenly"
            Arrangement.SpaceAround -> "SpaceAround"
            else -> "Start"
        }
        encoder.encodeString(arrangement)
    }

    override fun deserialize(decoder: Decoder): Arrangement.Horizontal {
        val value = decoder.decodeString()
        return when (value) {
            "Center" -> Arrangement.Center
            "End" -> Arrangement.End
            "SpaceBetween" -> Arrangement.SpaceBetween
            "SpaceEvenly" -> Arrangement.SpaceEvenly
            "SpaceAround" -> Arrangement.SpaceAround
            "Start" -> Arrangement.Start
            else -> Arrangement.Start
        }
    }
}