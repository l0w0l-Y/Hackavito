package ru.aleksandra.core.sdui.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SDUIApi(
    private val client: HttpClient
) {

    suspend fun getSDUIByName(name: String): String {
        return client.get(name).body()
    }
}