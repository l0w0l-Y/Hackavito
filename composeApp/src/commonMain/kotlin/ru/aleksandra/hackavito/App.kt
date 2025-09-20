package ru.aleksandra.hackavito

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.aleksandra.core.sdui.presentation.SDUIScreen
import ru.aleksandra.core.theme.AvitoTheme

@Composable
@Preview
fun App() {
    AvitoTheme {
        val navController = rememberNavController()
        Scaffold { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                NavHost(navController, startDestination = "sdui/main") {
                    composable(
                        "sdui/{name}",
                        arguments = listOf(navArgument("name") { type = NavType.StringType })
                    ) {
                        SDUIScreen(navController)
                    }
                }
            }
        }
    }
}