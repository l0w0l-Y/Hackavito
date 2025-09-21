package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

object TextIndentSerializer : KSerializer<TextIndent> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("TextIndent") {
            element("firstLine", TextUnitSerializer.descriptor)
            element("restLine", TextUnitSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): TextIndent {
        val (firstLine, restLine) = decoder.decodeStructure(descriptor) {
            val firstLine = decodeSerializableElement(descriptor, 0, TextUnitSerializer)
            val restLine = decodeSerializableElement(descriptor, 1, TextUnitSerializer)
            firstLine to restLine
        }
        return TextIndent(firstLine = firstLine, restLine = restLine)
    }

    override fun serialize(encoder: Encoder, value: TextIndent) {
        val firstLine = if (value.firstLine.isUnspecified) 0.sp else value.firstLine
        val restLine = if (value.restLine.isUnspecified) 0.em else value.restLine
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, TextUnitSerializer, firstLine)
            encodeSerializableElement(descriptor, 1, TextUnitSerializer, restLine)
        }
    }
}