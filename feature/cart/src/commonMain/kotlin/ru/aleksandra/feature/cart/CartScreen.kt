package ru.aleksandra.feature.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import ru.aleksandra.core.ui.AvitoCartItem
import ru.aleksandra.core.ui.AvitoNavBar
import ru.aleksandra.core.ui.AvitoSelectAll
import ru.aleksandra.core.ui.AvitoShopName

@Composable
fun CartScreen() {
    Column {
//        AvitoNavBar()
        AvitoSelectAll(
            isChecked = true,
            onCheckedChange = {},
            deleteCount = 3,
            onDeleteClick = {})
        AvitoShopName(isChecked = true,
            onCheckedChange = {},
            shopName = "Paer Store",
            rating = 4.89f,
            reviewsCount = 643
            )
        AvitoCartItem(
            isChecked = true,
            onCheckedChange = {},
            itemName = "Зарядка MagSafe Charger 15W 1 метр",
            price = 9979.9f,
            salePercent = 5,
            itemCount = 2,
            onPlusItemCountClicked = {},
            onMinusItemCountClicked = {}
            )
    }
}