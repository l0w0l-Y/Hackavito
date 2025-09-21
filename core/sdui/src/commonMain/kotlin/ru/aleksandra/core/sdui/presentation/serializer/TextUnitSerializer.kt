package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TextUnitSerializer : KSerializer<TextUnit> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontWeight", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TextUnit {
        val value = decoder.decodeString()
        return when {
            value.endsWith("sp") -> value.removeSuffix("sp").toFloat().sp
            value.endsWith("em") -> value.removeSuffix("em").toFloat().em
            else -> error("Неизвестная единица измерения: $value")
        }
    }

    override fun serialize(encoder: Encoder, value: TextUnit) {
        val value = value.value.toInt()
        encoder.encodeInt(value)
    }
}