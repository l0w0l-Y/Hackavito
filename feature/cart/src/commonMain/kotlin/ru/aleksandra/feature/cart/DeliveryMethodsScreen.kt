package ru.aleksandra.feature.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ru.aleksandra.core.theme.green800
import ru.aleksandra.core.theme.violet500
import ru.aleksandra.core.ui.AvitoCheckoutNavBar
import ru.aleksandra.core.ui.AvitoDeliveryAddressesMethod
import ru.aleksandra.core.ui.AvitoDeliveryAddressesNavBar
import ru.aleksandra.core.ui.model.DeliveryVariant

@Composable
fun DeliveryMethodsScreen() {
    Column() {
        AvitoDeliveryAddressesNavBar()
        AvitoDeliveryAddressesMethod(
            deliveryVariantsList = listOf(
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
        )
    }
}