package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.style.TextAlign
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TextAlignSerializer : KSerializer<TextAlign> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TextAlign", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TextAlign) {
        when (value) {
            TextAlign.Left -> encoder.encodeString("Left")
            TextAlign.Right -> encoder.encodeString("Right")
            TextAlign.Center -> encoder.encodeString("Center")
            TextAlign.Justify -> encoder.encodeString("Justify")
            TextAlign.Start -> encoder.encodeString("Start")
            TextAlign.End -> encoder.encodeString("End")
            else -> encoder.encodeString("Justify")
        }
    }

    override fun deserialize(decoder: Decoder): TextAlign {
        val value = decoder.decodeString()
        return when (value) {
            "Left" -> TextAlign.Left
            "Right" -> TextAlign.Right
            "Center" -> TextAlign.Center
            "Justify" -> TextAlign.Justify
            "Start" -> TextAlign.Start
            "End" -> TextAlign.End
            else -> TextAlign.Justify
        }
    }
}