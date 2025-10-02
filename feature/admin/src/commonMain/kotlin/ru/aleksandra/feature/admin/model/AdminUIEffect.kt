package ru.aleksandra.feature.admin.model

sealed class AdminUIEffect {
    data class UpdateJson(val json: String) : AdminUIEffect()
}