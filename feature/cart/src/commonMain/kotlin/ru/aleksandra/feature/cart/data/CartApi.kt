package ru.aleksandra.feature.cart.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.aleksandra.feature.cart.data.model.Cart

class CartApi(private val client: HttpClient) {

    suspend fun getCartItems(): Cart {
        return client.get("/data/cart").body<Cart>()
    }
}