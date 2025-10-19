package ru.aleksandra.feature.cart.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import ru.aleksandra.feature.cart.data.model.Cart

class CartApi(private val client: HttpClient) {

    suspend fun getCartItems(): Cart {
        return client.get("/data/cart").body<Cart>()
    }

    suspend fun selectStore(storeId: String): Cart {
       return client.post("/data/selectStore?storeId=$storeId").body<Cart>()
    }

    suspend fun selectAll(): Cart {
       return client.post("/data/selectAll").body<Cart>()
    }
}