plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    kotlin("plugin.serialization")
    application
}

group = "ru.aleksandra.hackavito"
version = "1.0.0"
application {
    mainClass.set("ru.aleksandra.hackavito.ApplicationKt")
   // applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
   // implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.test.host)

    implementation("io.ktor:ktor-server-content-negotiation:3.1.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:1.8.1")
    implementation(libs.kotlinx.serialization.json)
    implementation("io.ktor:ktor-server-cors:3.1.2")
}