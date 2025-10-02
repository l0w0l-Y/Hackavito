package ru.aleksandra.core.sdui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavController
import ru.aleksandra.core.sdui.presentation.model.UIEffect
import ru.aleksandra.core.sdui.presentation.ui.Renderer

@Composable
fun SDUIViewModel.bind(
    navController: NavController,
) {
    val uriHandler = LocalUriHandler.current
    val state by ui.collectAsState()

    LaunchedEffect(Unit) {
        sideEffects.collect {
            when (it) {
                is UIEffect.NavigateEffect -> {
                    navController.navigate(NavigationDestination.SDUIScreen(it.destination))
                }

                is UIEffect.OpenUrlEffect -> {
                    uriHandler.openUri(it.url)
                }

                is UIEffect.PopBackEffect -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Renderer(state, handleAction = {
        handleAction(it)
    })
}