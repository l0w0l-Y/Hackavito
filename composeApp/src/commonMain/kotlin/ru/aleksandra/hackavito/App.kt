package ru.aleksandra.hackavito

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.aleksandra.core.theme.AvitoTheme
import ru.aleksandra.feature.cart.CartScreen

@Composable
@Preview
fun App() {
    AvitoTheme {
        CartScreen()
    }
}