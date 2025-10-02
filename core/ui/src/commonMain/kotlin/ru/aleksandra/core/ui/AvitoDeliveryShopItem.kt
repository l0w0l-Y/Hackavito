package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.Image
import coil3.compose.AsyncImage
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.ic_order
import hackavito.core.ui.generated.resources.ic_three_dots
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.bgPage
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.theme.white
import ru.aleksandra.core.theme.xs10
import ru.aleksandra.core.ui.model.DeliveryVariant
import ru.aleksandra.core.ui.model.Item

@Composable
fun AvitoDeliveryShopItem(
    itemName: String,
    itemImage: Image,
    itemCount: Int,
    shopName: String,
    deliveryVariant: DeliveryVariant
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.controlBgDefault)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .background(color = MaterialTheme.colorScheme.bgPage, shape = RoundedCornerShape(16.dp))
            .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 24.dp)
    ) {
        Row {
            Text(
                text = shopName,
                style = MaterialTheme.typography.h30,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
            Icon(
                painter = painterResource(Res.drawable.ic_three_dots),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .size(24.dp)
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_order),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(20.dp)
            )
            Text(
                text = shopName,
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 32.dp, top = 10.dp)
        ) {
            AsyncImage(
                model = itemImage,
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp) // квадрат
                    .clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillWidth
            )
            Box(modifier = Modifier
                .padding(start = 4.dp, top = 37.dp)
                .height(15.dp)
                .background(color = MaterialTheme.colorScheme.contentPrimary)
            ){
                Text(
                    text = itemCount.toString(),
                    style = MaterialTheme.typography.xs10,
                    color = MaterialTheme.colorScheme.white
                )
            }
        }
    }
}