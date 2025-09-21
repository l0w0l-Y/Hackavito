package ru.aleksandra.core.sdui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import ru.aleksandra.core.sdui.presentation.model.UIEffect
import ru.aleksandra.core.sdui.presentation.ui.Renderer

@Composable
fun SDUIScreen(
    navController: NavController,
    sDUIViewModel: SDUIViewModel = koinViewModel(),
) {
    val uriHandler = LocalUriHandler.current
    val state by sDUIViewModel.ui.collectAsState()

    LaunchedEffect(Unit) {
        sDUIViewModel.sideEffects.collect {
            when (it) {
                is UIEffect.NavigateEffect -> {
                    navController.navigate(NavigationDestination.SDUIScreen(it.destination))
                }

                is UIEffect.OpenUrlEffect -> {
                    uriHandler.openUri(it.url)
                }
            }
        }
    }

    Renderer(state, handleAction = {
        sDUIViewModel.handleAction(it)
    })
}