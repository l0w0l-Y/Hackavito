package ru.aleksandra.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.all
import hackavito.core.ui.generated.resources.at_pick_up_points
import hackavito.core.ui.generated.resources.avito_delivery_in_moscow
import hackavito.core.ui.generated.resources.change_recipient
import hackavito.core.ui.generated.resources.courier
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.black
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.gray54
import ru.aleksandra.core.theme.gray92
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.theme.warmgray4
import ru.aleksandra.core.theme.white
import ru.aleksandra.core.ui.model.DeliveryVariant

@Composable
fun AvitoDeliveryAddressesMethod(
    deliveryVariantsList: List<DeliveryVariant>,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 16.dp, end = 16.dp)
    ) {
        Row (
            modifier = Modifier.padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)
        ){
            Button(
                onClick = { selectedIndex = 0 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedIndex == 0) MaterialTheme.colorScheme.gray92 else MaterialTheme.colorScheme.warmgray4,
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(
                    top = 11.dp,
                    bottom = 13.dp,
                    start = 16.dp,
                    end = 17.dp
                ),
                modifier = Modifier
                    .padding(end = 6.dp)
            ) {
                Text(
                    text = stringResource(Res.string.all),
                    style = MaterialTheme.typography.m20,
                    color = if (selectedIndex == 0) MaterialTheme.colorScheme.white else MaterialTheme.colorScheme.black,
                )
            }
            Button(
                onClick = { selectedIndex = 1 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedIndex == 1) MaterialTheme.colorScheme.gray92 else MaterialTheme.colorScheme.warmgray4,
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(
                    top = 11.dp,
                    bottom = 13.dp,
                    start = 16.dp,
                    end = 17.dp
                ),
                modifier = Modifier
                    .padding(end = 6.dp)
            ) {
                Text(
                    text = stringResource(Res.string.at_pick_up_points),
                    style = MaterialTheme.typography.m20,
                    color = if (selectedIndex == 1) MaterialTheme.colorScheme.white else MaterialTheme.colorScheme.black,
                )
            }
            Button(
                onClick = { selectedIndex = 2 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedIndex == 2) MaterialTheme.colorScheme.gray92 else MaterialTheme.colorScheme.warmgray4,
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(
                    top = 11.dp,
                    bottom = 13.dp,
                    start = 16.dp,
                    end = 17.dp
                ),
                modifier = Modifier
                    .padding(end = 6.dp)
            ) {
                Text(
                    text = stringResource(Res.string.courier),
                    style = MaterialTheme.typography.m20,
                    color = if (selectedIndex == 2) MaterialTheme.colorScheme.white else MaterialTheme.colorScheme.black,
                )
            }
        }
        Text(
            text = stringResource(Res.string.avito_delivery_in_moscow),
            style = MaterialTheme.typography.m20,
            color = MaterialTheme.colorScheme.gray54,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        deliveryVariantsList.forEachIndexed { index, deliveryVariantItem ->
            AvitoDeliveryAddressesMethodItem(
                deliveryVariant = deliveryVariantItem
            )
        }
    }
}