package ru.aleksandra.feature.admin

sealed class AdminUIEffect {
    data class UpdateJson(val json: String) : AdminUIEffect()
}