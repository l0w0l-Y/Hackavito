package ru.aleksandra.feature.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import ru.aleksandra.core.ui.AvitoNavBar
import ru.aleksandra.core.ui.AvitoSelectAll

@Composable
fun CartScreen() {
    Column {
//        AvitoNavBar()
        AvitoSelectAll(
            isChecked = true,
            onCheckedChange = {},
            deleteCount = 3,
            onDeleteClick = {})
    }
}