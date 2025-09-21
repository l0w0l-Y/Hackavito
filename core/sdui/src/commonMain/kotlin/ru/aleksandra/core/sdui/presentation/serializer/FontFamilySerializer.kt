package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.font.FontFamily
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object FontFamilySerializer : KSerializer<FontFamily> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontFamily", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: FontFamily) {
        val name = when(value){
            FontFamily.Monospace -> "Monospace"
            FontFamily.Serif -> "Serif"
            FontFamily.SansSerif -> "SansSerif"
            FontFamily.Cursive -> "Cursive"
            else -> "Default"
        }
        encoder.encodeString(name)
    }

    //TODO: Добавить поддержку кастомных шрифтов из ресурсов
    override fun deserialize(decoder: Decoder): FontFamily {
        val name = decoder.decodeString()
        return when(name){
            "Monospace" -> FontFamily.Monospace
            "Serif" -> FontFamily.Serif
            "SansSerif" -> FontFamily.SansSerif
            "Cursive" -> FontFamily.Cursive
            else -> FontFamily.Default
        }
    }
}
