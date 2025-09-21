package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object FontSynthesisSerializer : KSerializer<FontSynthesis> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontSynthesis", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): FontSynthesis {
        val value = decoder.decodeString()
        return when (value) {
            "None" -> FontSynthesis.None
            "Weight" -> FontSynthesis.Weight
            "Style" -> FontSynthesis.Style
            "All" -> FontSynthesis.All
            else -> FontSynthesis.None
        }
    }

    override fun serialize(encoder: Encoder, value: FontSynthesis) {
        val value = when (value) {
            FontSynthesis.None -> "None"
            FontSynthesis.Weight -> "Weight"
            FontSynthesis.Style -> "Style"
            FontSynthesis.All -> "All"
            else -> "None"
        }
        encoder.encodeString(value)
    }
}