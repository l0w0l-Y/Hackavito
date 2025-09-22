package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.Alignment
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AlignmentHorizontalSerializer : KSerializer<Alignment.Horizontal> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AlignmentHorizontal", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Alignment.Horizontal) {
        val alignment = when (value) {
            Alignment.Start -> "Start"
            Alignment.CenterHorizontally -> "CenterHorizontally"
            Alignment.End -> "End"
            else -> "Start"
        }
        encoder.encodeString(alignment)
    }

    override fun deserialize(decoder: Decoder): Alignment.Horizontal {
        val value = decoder.decodeString()
        return when (value) {
            "CenterHorizontally" -> Alignment.CenterHorizontally
            "End" -> Alignment.End
            "Start" -> Alignment.Start
            else -> Alignment.Start
        }
    }
}