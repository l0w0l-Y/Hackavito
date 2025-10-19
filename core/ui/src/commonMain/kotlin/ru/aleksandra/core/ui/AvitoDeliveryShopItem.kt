package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.Image
import coil3.compose.AsyncImage
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.amount_with_ruble
import hackavito.core.ui.generated.resources.from_to_days_with_comma_before
import hackavito.core.ui.generated.resources.ic_crossing_price
import hackavito.core.ui.generated.resources.ic_order
import hackavito.core.ui.generated.resources.ic_arrow_forward
import hackavito.core.ui.generated.resources.ic_navigator
import hackavito.core.ui.generated.resources.ic_three_dots
import io.ktor.util.network.NetworkAddress
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.bgPage
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.contentSecondary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.h70
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.theme.red600
import ru.aleksandra.core.theme.s20
import ru.aleksandra.core.theme.white
import ru.aleksandra.core.theme.xs10
import ru.aleksandra.core.ui.model.DeliveryVariant
import ru.aleksandra.core.ui.model.Item

@Composable
fun AvitoDeliveryShopItem(
    itemName: String,
    itemImage: String,
    itemCount: Int,
    shopName: String,
    address: String,
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
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(Res.drawable.ic_three_dots),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .size(24.dp)
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
                text = itemName,
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
            if (itemCount > 1) {
                Box(
                    modifier = Modifier
                        .padding(start = 4.dp, top = 37.dp)
                        .height(15.dp)
                        .widthIn(min = 15.dp)
                        .background(
                            color = MaterialTheme.colorScheme.contentPrimary,
                            shape = RoundedCornerShape(48.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = itemCount.toString(),
                        style = MaterialTheme.typography.xs10,
                        color = MaterialTheme.colorScheme.white,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_navigator),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(20.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = deliveryVariant.name,
                    style = MaterialTheme.typography.m20,
                    color = MaterialTheme.colorScheme.contentPrimary,
                )
                Text(
                    text = address,
                    style = MaterialTheme.typography.m20,
                    color = MaterialTheme.colorScheme.contentSecondary,
                )
                Row(
                    modifier = Modifier.padding()
                ) {
                    if (deliveryVariant.priceWithoutDiscount == null) {
                        Text(
                            text = stringResource(
                                Res.string.amount_with_ruble,
                                deliveryVariant.price
                            ),
                            style = MaterialTheme.typography.m20,
                            color = MaterialTheme.colorScheme.contentPrimary,
                        )
                        Text(
                            text = stringResource(
                                Res.string.from_to_days_with_comma_before,
                                deliveryVariant.fromDays,
                                deliveryVariant.toDays
                            ),
                            style = MaterialTheme.typography.m20,
                            color = MaterialTheme.colorScheme.contentPrimary
                        )
                    } else {
                        Box(
                            modifier = Modifier.size(width = 40.dp, height = 10.dp)
                        ) {
                            Text(
                                text = stringResource(
                                    Res.string.amount_with_ruble,
                                    deliveryVariant.priceWithoutDiscount
                                ),
                                style = MaterialTheme.typography.m20,
                                color = MaterialTheme.colorScheme.contentPrimary
                            )
                            Icon(
                                painter = painterResource(Res.drawable.ic_crossing_price),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.red600,
                                modifier = Modifier
//                                .padding(top = 7.97.dp, bottom = 4.9.dp)
//                                .size(width = 44.92.dp, height = 4.1.dp)
                            )
                        }
                        Text(
                            text = deliveryVariant.price.toString(),
                            style = MaterialTheme.typography.m20,
                            color = MaterialTheme.colorScheme.red600,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = "₽",
                            style = MaterialTheme.typography.m20,
                            color = MaterialTheme.colorScheme.red600,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                        Text(
                            text = stringResource(
                                Res.string.from_to_days_with_comma_before,
                                deliveryVariant.fromDays,
                                deliveryVariant.toDays
                            ),
                            style = MaterialTheme.typography.m20,
                            color = MaterialTheme.colorScheme.contentPrimary
                        )
                    }

                }

            }
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_forward),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(height = 20.dp, width = 8.dp)
            )

        }
    }
}