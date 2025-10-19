package ru.aleksandra.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.amount_with_ruble
import hackavito.core.ui.generated.resources.from_amount_with_ruble
import hackavito.core.ui.generated.resources.from_to_days
import hackavito.core.ui.generated.resources.from_to_days_with_comma_before
import hackavito.core.ui.generated.resources.image_navigator_in_circle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.dayBlack
import ru.aleksandra.core.theme.gray54
import ru.aleksandra.core.theme.h50
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.ui.model.DeliveryVariant

@Composable
fun AvitoDeliveryAddressesMethodItem(
    deliveryVariant: DeliveryVariant,
) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.image_navigator_in_circle),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 12.dp)
                .size(44.dp)
        )

        Column(
            modifier = Modifier
                .padding(end = 12.dp)
                .weight(1f)
        ) {
            Text(
                text = deliveryVariant.name,
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.dayBlack
            )
            Text(
                modifier = Modifier.padding(top = 3.dp),
                text = stringResource(
                    Res.string.from_to_days,
                    deliveryVariant.fromDays,
                    deliveryVariant.toDays
                ),
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.gray54
            )
            if (deliveryVariant.description != null && deliveryVariant.descriptionColor != null) {
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    text = deliveryVariant.description,
                    style = MaterialTheme.typography.m20,
                    color = deliveryVariant.descriptionColor
                )
            }
        }
        Row() {
            if (deliveryVariant.priceWithoutDiscount != null) {
                Text(
                    modifier = Modifier.padding(end = 6.dp),
                    text = stringResource(
                        Res.string.amount_with_ruble,
                        deliveryVariant.priceWithoutDiscount.toString()
                    ),
                    style = MaterialTheme.typography.m20,
                    color = MaterialTheme.colorScheme.gray54,
                )
            }

            Text(
                text = stringResource(
                    Res.string.from_amount_with_ruble,
                    deliveryVariant.price.toString()
                ),
                style = MaterialTheme.typography.h50,
                color = MaterialTheme.colorScheme.dayBlack,
            )
        }
    }
}