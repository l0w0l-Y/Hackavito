package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ShadowSerializer : KSerializer<Shadow> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Shadow") {
            element("color", ColorSerializer.descriptor)
            element("offset", OffsetSerializer.descriptor)
            element("blurRadius", PrimitiveSerialDescriptor("blurRadius", PrimitiveKind.FLOAT))
        }

    override fun serialize(encoder: Encoder, value: Shadow) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeSerializableElement(descriptor, 0, ColorSerializer, value.color)
        composite.encodeSerializableElement(descriptor, 1, OffsetSerializer, value.offset)
        composite.encodeFloatElement(descriptor, 2, value.blurRadius)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): Shadow {
        val dec = decoder.beginStructure(descriptor)
        var color = Color.Unspecified
        var offset = Offset(0f, 0f)
        var blurRadius = 0f
        while (true) {
            when (val index = dec.decodeElementIndex(descriptor)) {
                0 -> color = dec.decodeSerializableElement(descriptor, 0, ColorSerializer)
                1 -> offset = dec.decodeSerializableElement(descriptor, 1, OffsetSerializer)
                2 -> blurRadius = dec.decodeFloatElement(descriptor, 2)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        dec.endStructure(descriptor)
        return Shadow(color, offset, blurRadius)
    }
}