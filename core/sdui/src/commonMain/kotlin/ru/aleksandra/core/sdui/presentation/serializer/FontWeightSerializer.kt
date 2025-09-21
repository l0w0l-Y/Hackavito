package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.font.FontWeight
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object FontWeightSerializer : KSerializer<FontWeight> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontWeight", PrimitiveKind.STRING)

    private val map: Map<String, FontWeight> = mapOf(
        "W100" to FontWeight.W100,
        "W200" to FontWeight.W200,
        "W300" to FontWeight.W300,
        "W400" to FontWeight.W400,
        "Normal" to FontWeight.Normal,
        "Medium" to FontWeight.Medium,
        "W500" to FontWeight.W500,
        "SemiBold" to FontWeight.SemiBold,
        "W600" to FontWeight.W600,
        "Bold" to FontWeight.Bold,
        "W700" to FontWeight.W700,
        "ExtraBold" to FontWeight.ExtraBold,
        "W800" to FontWeight.W800,
        "Black" to FontWeight.Black,
        "W900" to FontWeight.W900
    )

    override fun deserialize(decoder: Decoder): FontWeight {
        val value = decoder.decodeString()
        return map[value] ?: FontWeight.Normal
    }

    override fun serialize(encoder: Encoder, value: FontWeight) {
        val name = map.entries.firstOrNull { it.value == value }?.key ?: "Normal"
        encoder.encodeString(name)
    }
}