package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.Alignment
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AlignmentSerializer : KSerializer<Alignment> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Alignment", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Alignment) {
        val alignment = when (value) {
            Alignment.TopStart -> "TopStart"
            Alignment.TopCenter -> "TopCenter"
            Alignment.TopEnd -> "TopEnd"
            Alignment.CenterStart -> "CenterStart"
            Alignment.Center -> "Center"
            Alignment.CenterEnd -> "CenterEnd"
            Alignment.BottomStart -> "BottomStart"
            Alignment.BottomCenter -> "BottomCenter"
            Alignment.BottomEnd -> "BottomEnd"
            else -> error("Нет такого alignment: $value")
        }
        encoder.encodeString(alignment)
    }

    override fun deserialize(decoder: Decoder): Alignment {
        val value = decoder.decodeString()
        return when (value) {
            "TopStart" -> Alignment.TopStart
            "TopCenter" -> Alignment.TopCenter
            "TopEnd" -> Alignment.TopEnd
            "CenterStart" -> Alignment.CenterStart
            "Center" -> Alignment.Center
            "CenterEnd" -> Alignment.CenterEnd
            "BottomStart" -> Alignment.BottomStart
            "BottomCenter" -> Alignment.BottomCenter
            "BottomEnd" -> Alignment.BottomEnd
            else -> error("Нет такого alignment: $value")
        }
    }
}