package ru.aleksandra.feature.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.aleksandra.core.ui.AvitoAddItem
import ru.aleksandra.core.ui.AvitoCartItem
import ru.aleksandra.core.ui.AvitoNavBar
import ru.aleksandra.core.ui.AvitoSelectAll
import ru.aleksandra.core.ui.AvitoShopName
import ru.aleksandra.core.ui.model.Item

@Composable
fun CartScreen() {
    Column (
        modifier = Modifier.safeDrawingPadding()
    ){
        AvitoNavBar()
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
            item = Item(name = "Зарядка MagSafe Charger 15W 1 метр", priceWithDiscount = 9900f, priceWithoutDiscount = 9405f, salePercent = 5),
            itemCount = 2,
            onPlusItemCountClicked = {},
            onMinusItemCountClicked = {}
            )
        AvitoAddItem(
            itemsBeforeDiscountCount = 1,
            salePercent = 5
        )
    }
}