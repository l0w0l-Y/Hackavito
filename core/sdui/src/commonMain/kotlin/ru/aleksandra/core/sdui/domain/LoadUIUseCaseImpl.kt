package ru.aleksandra.core.sdui.domain

import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent

class LoadUIUseCaseImpl() : LoadUIUseCase {
    override suspend fun loadUI(screenName: String): Flow<SDUIComponent> {
        delay(2000)
        val value = if (screenName == "main") {
            SDUIComponent.Row(
                modifier = emptyList(),
                children = listOf(
                    SDUIComponent.Text(
                        modifier = emptyList(),
                        value = "Welcome to Compose Multiplatform!",
                        style = TextStyle.Default
                    ),
                    SDUIComponent.Button(
                        modifier = emptyList(),
                        title = "Hello",
                        action = Action.OpenUrl("https://developer.android.com")
                    )
                )
            )
        } else {
            SDUIComponent.Text(
                modifier = emptyList(),
                value = "Screen not found",
                style = TextStyle.Default
            )
        }
        return flowOf(value)
    }

}

interface LoadUIUseCase {
    suspend fun loadUI(screenName: String): Flow<SDUIComponent>
}