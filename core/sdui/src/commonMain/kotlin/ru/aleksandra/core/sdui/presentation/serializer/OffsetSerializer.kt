package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.geometry.Offset
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object OffsetSerializer : KSerializer<Offset> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Offset") {
            element("x", PrimitiveSerialDescriptor("x", PrimitiveKind.FLOAT))
            element("y", PrimitiveSerialDescriptor("y", PrimitiveKind.FLOAT))
        }

    override fun deserialize(decoder: Decoder): Offset {
        val dec = decoder.beginStructure(descriptor)
        var x = 0f
        var y = 0f
        while (true) {
            when (val index = dec.decodeElementIndex(TextGeometricTransformSerializer.descriptor)) {
                0 -> x = dec.decodeFloatElement(TextGeometricTransformSerializer.descriptor, 0)
                1 -> y = dec.decodeFloatElement(TextGeometricTransformSerializer.descriptor, 1)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        dec.endStructure(descriptor)
        return Offset(x, y)
    }

    override fun serialize(encoder: Encoder, value: Offset) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeFloatElement(descriptor, 0, value.x)
        composite.encodeFloatElement(descriptor, 1, value.y)
        composite.endStructure(descriptor)
    }
}