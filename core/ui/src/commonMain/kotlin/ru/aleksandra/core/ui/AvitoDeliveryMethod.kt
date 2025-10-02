package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.for_address
import org.jetbrains.compose.resources.stringResource
import hackavito.core.ui.generated.resources.obtaining_method
import hackavito.core.ui.generated.resources.ic_location
import hackavito.core.ui.generated.resources.ic_expand_more
import org.jetbrains.compose.resources.painterResource

import ru.aleksandra.core.theme.bgBase
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.ui.model.Delivery

@Composable
fun AvitoDeliveryMethod(
    delivery: Delivery,
    selectedDeliveryVariantIndex: Int? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, bottom = 16.dp),
    ) {
        Text(
            text = stringResource(Res.string.obtaining_method),
            style = MaterialTheme.typography.h30,
            color = MaterialTheme.colorScheme.contentPrimary,
            modifier = Modifier.padding(start = 6.dp, end = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 4.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(Res.string.for_address),
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
                )
            Icon(
                painter = painterResource(Res.drawable.ic_location),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(width = 12.dp, height = 20.dp)
                    .padding(vertical = 2.dp)
            )
            Text(
                text = delivery.address,
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
            Icon(
                painter = painterResource(Res.drawable.ic_expand_more),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(width = 10.dp, height = 20.dp)
            )
        }
        LazyRow (
            modifier = Modifier.padding(top = 12.dp)
        ) {
            items(delivery.deliveryVariant.size) { index ->
                DeliveryMethodItem(
                    deliveryVariant = delivery.deliveryVariant[index],
                    isSelected = if (selectedDeliveryVariantIndex != null) {
                        selectedDeliveryVariantIndex == index
                    } else {
                        false
                    }
                )
            }
        }
    }
}