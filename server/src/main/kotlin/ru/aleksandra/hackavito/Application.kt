package ru.aleksandra.hackavito

import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import ru.aleksandra.hackavito.model.Cart
import ru.aleksandra.hackavito.model.CartItem
import ru.aleksandra.hackavito.model.Item
import ru.aleksandra.hackavito.model.Store
import java.io.File

var version = 1

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowHeader(io.ktor.http.HttpHeaders.ContentType)
        allowOrigins({ it == "http://localhost:8081" })
        allowCredentials = true
    }

    routing {
        get("/delivery/pay/cart") {
            call.respondText(readJson("server/src/main/kotlin/ru/aleksandra/hackavito/json/AvitoPayCart_$version.json"))
        }

        get("/data/cart") {
            call.respondText(
                Json.encodeToString(
                    Cart(
                        items = listOf(
                            CartItem(
                                store = Store(
                                    id = "1",
                                    "Pear Store",
                                    rating = 4.8f,
                                    643
                                ),
                                items = listOf(
                                    Item(
                                        id = "1",
                                        name = "Зарядка MagSafe Charger 15W 1 метр",
                                        priceWithDiscount = 4990,
                                        salePercent = 5,
                                        priceWithoutDiscount = 9481,
                                        count = 1,
                                        imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                                    ),
                                    Item(
                                        id = "2",
                                        name = "AirPods Pro 2",
                                        priceWithDiscount = 15191,
                                        salePercent = 5,
                                        priceWithoutDiscount = 15990,
                                        count = 1,
                                        imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                                    )
                                )
                            ),
                            CartItem(
                                store = Store(
                                    id = "2",
                                    "TECHNO ZONE",
                                    rating = 5.0f,
                                    916
                                ),
                                items = listOf(
                                    Item(
                                        id = "1",
                                        name = "Зарядка MagSafe Charger 15W 1 метр",
                                        priceWithDiscount = 4990,
                                        salePercent = 5,
                                        priceWithoutDiscount = 9481,
                                        count = 1,
                                        imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                                    ),
                                    Item(
                                        id = "2",
                                        name = "AirPods Pro 2",
                                        priceWithDiscount = 15191,
                                        salePercent = 5,
                                        priceWithoutDiscount = 15990,
                                        count = 1,
                                        imageUrl = "https://image.kazanexpress.ru/ce1555j2fmcqfdjtaulg/t_product_high.jpg"
                                    )
                                )
                            )
                        ),
                        isSelected = false,
                        totalItems = 3,
                        totalPrice = 0
                    )
                ),
                contentType = ContentType.Application.Json
            )
        }

        post("/screen") {
            val screenId = call.request.queryParameters["screenId"] ?: error("screenId is null")
            val json = call.receiveText()
            version++
            val file =
                File("server/src/main/kotlin/ru/aleksandra/hackavito/json/${screenId}_$version.json")
            file.writeText(json)
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun readJson(name: String): String {
    return File(name).readText()
}

fun readJsonFromResources(name: String): String {
    val stream = {}.javaClass.classLoader.getResourceAsStream(name)
        ?: error("Resource $name not found")
    return stream.bufferedReader().use { it.readText() }
}