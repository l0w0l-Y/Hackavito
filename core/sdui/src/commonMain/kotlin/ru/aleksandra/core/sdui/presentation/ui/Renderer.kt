package ru.aleksandra.core.sdui.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.aakira.napier.Napier
import ru.aleksandra.core.sdui.presentation.model.Action
import ru.aleksandra.core.sdui.presentation.model.UIState

@Composable
fun Renderer(
    uiState: UIState,
    handleAction: (Action) -> Unit = {},
    showLoading: Boolean = true,
    loadingIndicator: @Composable () -> Unit = { },
) {
    when (uiState) {
        is UIState.Error -> {
            Text(uiState.message)
        }

        UIState.Init -> {}

        is UIState.Loaded -> {
                Render(uiState.ui, handleAction)
        }

        UIState.Loading -> {
            if (showLoading) {
                loadingIndicator()
            }
        }
    }
}