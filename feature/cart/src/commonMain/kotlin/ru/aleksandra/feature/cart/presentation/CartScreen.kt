package ru.aleksandra.feature.cart.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import ru.aleksandra.core.sdui.presentation.bind

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = koinViewModel(),
) {
    cartViewModel.bind(navController) {
        when (it.name) {
            "selectStore" -> {
                cartViewModel.selectStore("1")
            }

            "selectAll" -> {
                cartViewModel.selectAll()
            }
        }
    }
}