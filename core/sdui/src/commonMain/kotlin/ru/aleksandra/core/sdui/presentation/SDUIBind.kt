package ru.aleksandra.core.sdui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavController
import ru.aleksandra.core.sdui.presentation.NavigationDestination.*
import ru.aleksandra.core.sdui.presentation.model.UIEffect
import ru.aleksandra.core.sdui.presentation.ui.Renderer

@Composable
fun SDUIViewModel.bind(
    navController: NavController,
    handleCustomEffect: (UIEffect.CustomEffect) -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current
    val state by ui.collectAsState()

    LaunchedEffect(Unit) {
        sideEffects.collect {
            when (it) {
                is UIEffect.NavigateEffect -> {
                    navController.navigate(SDUIScreen(it.destination))
                }

                is UIEffect.OpenUrlEffect -> {
                    uriHandler.openUri(it.url)
                }

                is UIEffect.PopBackEffect -> {
                    navController.popBackStack()
                }

                is UIEffect.CustomEffect -> {
                    handleCustomEffect(it)
                }
            }
        }
    }

    Renderer(state, handleAction = {
        handleAction(it)
    })
}