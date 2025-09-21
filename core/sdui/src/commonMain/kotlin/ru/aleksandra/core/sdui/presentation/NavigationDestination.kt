package ru.aleksandra.core.sdui.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationDestination {
    @Serializable
    data class SDUIScreen(val screenId: String) : NavigationDestination()
}