package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.style.BaselineShift
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object BaselineShiftSerializer : KSerializer<BaselineShift> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontStyle", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): BaselineShift {
        val value = decoder.decodeString()
        return when (value) {
            "Superscript" -> BaselineShift.Superscript
            "Subscript" -> BaselineShift.Subscript
            else -> BaselineShift.None
        }
    }

    override fun serialize(encoder: Encoder, value: BaselineShift) {
        val baselineShift = when (value) {
            BaselineShift.Superscript -> "Superscript"
            BaselineShift.Subscript -> "Subscript"
            else -> "None"
        }
        encoder.encodeString(baselineShift)
    }
}