package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.ui.model.ShopWithItemForDelivery

@Composable
fun AvitoDeliveryShop(
    shopWithItemsForDeliveryList: List<ShopWithItemForDelivery>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.controlBgDefault)
            .padding(bottom = 16.dp),
    ) {
        items(shopWithItemsForDeliveryList.size) { index ->
            AvitoDeliveryShopItem(
                itemName = shopWithItemsForDeliveryList[index].item.name,
                itemImage = shopWithItemsForDeliveryList[index].item.image,
                itemCount = shopWithItemsForDeliveryList[index].item.count,
                shopName = shopWithItemsForDeliveryList[index].name,
                address = shopWithItemsForDeliveryList[index].address,
                deliveryVariant = shopWithItemsForDeliveryList[index].choosenDeliveryVariant
            )
        }


    }
}