package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.amount_with_ruble
import hackavito.core.ui.generated.resources.from_to_days_with_comma_before
import hackavito.core.ui.generated.resources.ic_checkmark
import hackavito.core.ui.generated.resources.ic_crossing_price
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.gray54
import ru.aleksandra.core.theme.h70
import ru.aleksandra.core.theme.red600
import ru.aleksandra.core.theme.s20
import ru.aleksandra.core.theme.xs10
import ru.aleksandra.core.ui.model.DeliveryVariant

@Composable
fun AvitoDeliveryMethodItem(
    deliveryVariant: DeliveryVariant,
    isSelected: Boolean = false,
) {
    Row(
        modifier = Modifier
            .padding(end = 6.dp)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) MaterialTheme.colorScheme.contentPrimary else MaterialTheme.colorScheme.controlBgDefault,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.controlBgDefault,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                start = 16.dp,
                top = 10.dp,
                bottom = 12.dp,
                end = 10.dp
            )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 2.dp,
                    end = if (isSelected) 8.dp else 26.dp
                )
        ) {
            if (deliveryVariant.description != null && deliveryVariant.descriptionColor != null){
                Text(
                    text = deliveryVariant.description,
                    style = MaterialTheme.typography.xs10,
                    color = deliveryVariant.descriptionColor
                )
            }
            Text(
                modifier = Modifier.padding(if (deliveryVariant.description != null && deliveryVariant.descriptionColor != null) 2.dp else 16.dp),
                text = deliveryVariant.name,
                style = MaterialTheme.typography.s20,
                color = MaterialTheme.colorScheme.gray54
            )
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                if (deliveryVariant.priceWithoutDiscount == null) {
                    Text(
                        text = deliveryVariant.price.toString(),
                        style = MaterialTheme.typography.s20,
                        color = MaterialTheme.colorScheme.contentPrimary,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text = "₽",
                        style = MaterialTheme.typography.s20,
                        color = MaterialTheme.colorScheme.contentPrimary
                    )
                    Text(
                        text = stringResource(
                            Res.string.from_to_days_with_comma_before,
                            deliveryVariant.fromDays,
                            deliveryVariant.toDays
                        ),
                        style = MaterialTheme.typography.s20,
                        color = MaterialTheme.colorScheme.contentPrimary
                    )
                } else {
                    Box(
                        modifier = Modifier.size(width = 45.dp, height = 16.dp)
                    ) {
                        Text(
                            text = stringResource(
                                Res.string.amount_with_ruble,
                                deliveryVariant.priceWithoutDiscount
                            ),
                            style = MaterialTheme.typography.s20,
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
                        style = MaterialTheme.typography.h70,
                        color = MaterialTheme.colorScheme.red600,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Text(
                        text = "₽",
                        style = MaterialTheme.typography.h70,
                        color = MaterialTheme.colorScheme.red600,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                    Text(
                        text = stringResource(
                            Res.string.from_to_days_with_comma_before,
                            deliveryVariant.fromDays,
                            deliveryVariant.toDays
                        ),
                        style = MaterialTheme.typography.h70,
                        color = MaterialTheme.colorScheme.contentPrimary
                    )
                }

            }
        }
        if (isSelected) {
            Icon(
                painter = painterResource(Res.drawable.ic_checkmark),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .size(18.dp)
            )
        }
    }
}