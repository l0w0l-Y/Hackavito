package ru.aleksandra.hackavito

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform