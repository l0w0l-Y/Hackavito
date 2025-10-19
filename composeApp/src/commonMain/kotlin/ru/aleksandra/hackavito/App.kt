package ru.aleksandra.hackavito

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.aleksandra.core.sdui.presentation.NavigationDestination
import ru.aleksandra.core.theme.AvitoTheme
import ru.aleksandra.core.theme.bgPage
import ru.aleksandra.feature.cart.presentation.CartScreen

@Composable
@Preview
fun App() {
    AvitoTheme {
        val navController = rememberNavController()
        Scaffold(
            containerColor = MaterialTheme.colorScheme.bgPage
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavigationDestination.SDUIScreen("/delivery/pay/cart")
                ) {
                    composable<NavigationDestination.SDUIScreen> {
                        CartScreen(navController)
                       // ru.aleksandra.feature.cart.DeliveryScreen()
                    }
                }
            }
        }
    }
}