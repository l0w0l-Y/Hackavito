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
    Column(
        modifier = Modifier.safeDrawingPadding()
    ) {
        AvitoNavBar("Корзина")
        AvitoSelectAll(
            isChecked = true,
            onCheckedChange = {},
            deleteCount = 3,
            onDeleteClick = {})
        AvitoShopName(
            isChecked = true,
            onCheckedChange = {},
            shopName = "Pear Store",
            rating = 4.89f,
            reviewsCount = 643
        )
        AvitoCartItem(
            isChecked = true,
            onCheckedChange = {},
            name = "Зарядка MagSafe Charger 15W 1 метр",
            priceWithDiscount = 9900,
            priceWithoutDiscount = 9405,
            salePercent = 5,
            count = 2,
            image = "https://90.img.avito.st/image/1/1.8BdQwLa4XP5maZ77PKvbCgRhXvjuYd72JmRe_OBpVPTm.7KxTjDbuHfpowy8-e-ZVx1Teq5MCi7g6EVTDMtXPLjA",
            onPlusItemCountClicked = {},
            onMinusItemCountClicked = {}
        )
        AvitoAddItem(
            itemsBeforeDiscountCount = 1,
            salePercent = 5
        )
    }
}