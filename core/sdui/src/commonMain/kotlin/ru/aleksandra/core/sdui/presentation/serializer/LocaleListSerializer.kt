package ru.aleksandra.core.sdui.presentation.serializer

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LocaleListSerializer : KSerializer<LocaleList> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("LocaleList") {
            element("locales", ListSerializer(String.serializer()).descriptor)
        }

    override fun serialize(encoder: Encoder, value: LocaleList) {
        val localesAsString = value.toList().map { it.toString() }
        encoder.encodeSerializableValue(ListSerializer(String.serializer()), localesAsString)
    }

    override fun deserialize(decoder: Decoder): LocaleList {
        val localeStrings = decoder.decodeSerializableValue(ListSerializer(String.serializer()))
        val locales = localeStrings.map { Locale(it) }
        return LocaleList(*locales.toTypedArray())
    }
}