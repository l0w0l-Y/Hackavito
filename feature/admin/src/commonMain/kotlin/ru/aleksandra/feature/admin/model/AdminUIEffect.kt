package ru.aleksandra.feature.admin.model

sealed class AdminUIEffect {
    data class UpdateJson(val json: String) : AdminUIEffect()
    data class SetJson(val json: String) : AdminUIEffect()
}