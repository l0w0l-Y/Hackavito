package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.style.TextGeometricTransform
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TextGeometricTransformSerializer : KSerializer<TextGeometricTransform> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("TextGeometricTransform") {
            element("scaleX", PrimitiveSerialDescriptor("scaleX", PrimitiveKind.FLOAT))
            element("skewX", PrimitiveSerialDescriptor("skewX", PrimitiveKind.FLOAT))
        }

    override fun serialize(encoder: Encoder, value: TextGeometricTransform) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeFloatElement(descriptor, 0, value.scaleX)
        composite.encodeFloatElement(descriptor, 1, value.skewX)
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): TextGeometricTransform {
        val dec = decoder.beginStructure(descriptor)
        var scaleX = 1f
        var skewX = 0f
        while (true) {
            when (val index = dec.decodeElementIndex(descriptor)) {
                0 -> scaleX = dec.decodeFloatElement(descriptor, 0)
                1 -> skewX = dec.decodeFloatElement(descriptor, 1)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        dec.endStructure(descriptor)
        return TextGeometricTransform(scaleX, skewX)
    }
}