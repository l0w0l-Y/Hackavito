package ru.aleksandra.feature.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.aleksandra.core.theme.green800
import ru.aleksandra.core.theme.violet500
import ru.aleksandra.core.ui.AvitoAddItem
import ru.aleksandra.core.ui.AvitoCartItem
import ru.aleksandra.core.ui.AvitoCheckoutNavBar
import ru.aleksandra.core.ui.AvitoDeliveryMethod
import ru.aleksandra.core.ui.AvitoNavBar
import ru.aleksandra.core.ui.AvitoSelectAll
import ru.aleksandra.core.ui.AvitoShopName
import ru.aleksandra.core.ui.DeliveryMethodItem
import ru.aleksandra.core.ui.model.Delivery
import ru.aleksandra.core.ui.model.DeliveryVariant

@Composable
fun DeliveryScreen() {
    Column(
        modifier = Modifier.safeDrawingPadding()
    ) {
        AvitoCheckoutNavBar()
        AvitoDeliveryMethod(
            delivery = Delivery(
                "Москва, ул. Лесная, 7", deliveryVariant = listOf(
                    DeliveryVariant(
                        name = "Почта России",
                        price = 2701,
                        priceWithoutDiscount = null,
                        fromDays = 1,
                        toDays = 4,
                        description = "Сюда доставляли",
                        descriptionColor = MaterialTheme.colorScheme.violet500
                    ),

                    DeliveryVariant(
                        name = "Авито",
                        price = 1216,
                        priceWithoutDiscount = 5028,
                        fromDays = 1,
                        toDays = 4,
                        description = "Лучшая цена",
                        descriptionColor = MaterialTheme.colorScheme.green800
                    ),
                    DeliveryVariant(
                        name = "Почта России",
                        price = 2701,
                        priceWithoutDiscount = null,
                        fromDays = 1,
                        toDays = 4,
                        description = "Сюда доставляли",
                        descriptionColor = MaterialTheme.colorScheme.violet500
                    ),
                )
            ),
            selectedDeliveryVariantIndex = 1
        )
    }
}