package ru.aleksandra.feature.admin.data

import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AdminApi(
    val client: HttpClient,
) {
    suspend fun saveScreen(name: String, json: String) {
        client.post("screen") {
            parameter("screenId", name)
            setBody(json)
        }
    }
}