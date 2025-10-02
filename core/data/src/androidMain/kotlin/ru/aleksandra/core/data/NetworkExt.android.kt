package ru.aleksandra.core.data

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun builder(): HttpClient {
    return HttpClient {
        defaultRequest {
            url(api)
        }
        expectSuccess = true
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }
    }.also {
        Napier.base(DebugAntilog())
    }
}