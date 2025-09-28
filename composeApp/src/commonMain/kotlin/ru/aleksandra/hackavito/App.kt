package ru.aleksandra.hackavito

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.aleksandra.core.sdui.presentation.NavigationDestination
import ru.aleksandra.core.theme.AvitoTheme
import ru.aleksandra.feature.cart.presentation.CartScreen

@Composable
@Preview
fun App() {
    AvitoTheme {
        val navController = rememberNavController()
        Scaffold { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationDestination.SDUIScreen("cart")
                ) {
                    composable<NavigationDestination.SDUIScreen> {
                        CartScreen(navController)
                    }
                }
            }
        }
    }
}