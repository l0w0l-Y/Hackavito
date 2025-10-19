package ru.aleksandra.feature.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.aleksandra.core.theme.green800
import ru.aleksandra.core.theme.violet500
import ru.aleksandra.core.ui.AvitoCheckoutNavBar
import ru.aleksandra.core.ui.AvitoDeliveryMethod
import ru.aleksandra.core.ui.AvitoDeliveryPayment
import ru.aleksandra.core.ui.AvitoDeliveryRecipient
import ru.aleksandra.core.ui.AvitoDeliveryRecipientBottomSheet
import ru.aleksandra.core.ui.AvitoDeliveryShop
import ru.aleksandra.core.ui.AvitoDeliveryTotal
import ru.aleksandra.core.ui.model.Delivery
import ru.aleksandra.core.ui.model.DeliveryVariant
import ru.aleksandra.core.ui.model.Item
import ru.aleksandra.core.ui.model.Recipient
import ru.aleksandra.core.ui.model.ShopWithItemForDelivery

@Composable
fun DeliveryScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }
    var bottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

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

        AvitoDeliveryShop(
            shopWithItemsForDeliveryList = listOf(
                ShopWithItemForDelivery(
                    name = "Pear Store",
                    item = Item(
                        name = "Зарядка MagSafe Charger 15W 1 метр",
                        priceWithDiscount = 9900,
                        priceWithoutDiscount = 9405,
                        salePercent = 5,
                        count = 2,
                        image = "https://90.img.avito.st/image/1/1.8BdQwLa4XP5maZ77PKvbCgRhXvjuYd72JmRe_OBpVPTm.7KxTjDbuHfpowy8-e-ZVx1Teq5MCi7g6EVTDMtXPLjA",
                    ),
                    address = "Москва, ул.Лесная, 7",
                    choosenDeliveryVariant = DeliveryVariant(
                        name = "Авито",
                        price = 157,
                        priceWithoutDiscount = 789,
                        fromDays = 1,
                        toDays = 4,
                        description = "BOB",
                        descriptionColor = MaterialTheme.colorScheme.violet500
                    )
                ),
                ShopWithItemForDelivery(
                    name = "TECHNO ZONE",
                    item = Item(
                        name = "iPhone 16 Pro, 256 ГБ",
                        priceWithDiscount = 9900,
                        priceWithoutDiscount = 9405,
                        salePercent = 5,
                        count = 1,
                        image = "https://40.img.avito.st/image/1/1.5OF5HLa4SAhPtYoNPXec7iu9Sg7HvcoAD7hKCsm1QALP.ay7_c0Y3iqTLLPAHw_hJ_jjJo9qz_7ew5_j6fd8zjos",
                    ),
                    address = "Москва, ул.Лесная, 7",
                    choosenDeliveryVariant = DeliveryVariant(
                        name = "Авито",
                        price = 1059,
                        priceWithoutDiscount = 4239,
                        fromDays = 1,
                        toDays = 4,
                        description = "BOB",
                        descriptionColor = MaterialTheme.colorScheme.violet500
                    )
                ),
            )
        )
        AvitoDeliveryRecipient(
            recipient = Recipient(
                name = "Князева Екатерина",
                number = "+7 800 555-35-35",
                email = "catherineu@gmail.com"
            ),
            onChangeRecipientClick = { bottomSheet = true }
        )
        if (bottomSheet) {
            AvitoDeliveryRecipientBottomSheet(
                onDismiss = { bottomSheet = false },
                onSave = {_, _, _ -> } ,
                recipient = Recipient(
                    name = "Князева Екатерина",
                    number = "+7 800 555-35-35",
                    email = "catherineu@gmail.com"
                ),
            )
        }
        AvitoDeliveryPayment(
            onOpenAllClick = {},
            selectedIndex = selectedIndex,
            onSelectedIndexChange = { selectedIndex = it }
        )
        AvitoDeliveryTotal(
            selectedIndex = selectedIndex,
            totalPrice = 110687,
            onProceedToPaymentClick = { }
        )
    }
}