package ru.aleksandra.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.change_recipient
import hackavito.core.ui.generated.resources.ic_check
import hackavito.core.ui.generated.resources.ic_checkmark
import hackavito.core.ui.generated.resources.image_bank_avito
import hackavito.core.ui.generated.resources.image_bank_sbp
import hackavito.core.ui.generated.resources.image_bank_tbank
import hackavito.core.ui.generated.resources.instantly_via_sbp
import hackavito.core.ui.generated.resources.minus_hundred_rubles
import hackavito.core.ui.generated.resources.open_all
import hackavito.core.ui.generated.resources.pay_in_your_banks_app
import hackavito.core.ui.generated.resources.payment_method
import hackavito.core.ui.generated.resources.quickly_top_up_via_sbp
import hackavito.core.ui.generated.resources.recipient
import hackavito.core.ui.generated.resources.sbp
import hackavito.core.ui.generated.resources.tbank
import hackavito.core.ui.generated.resources.wallet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.contentSecondary
import ru.aleksandra.core.theme.controlBgCheck
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.controlBgFaint
import ru.aleksandra.core.theme.controlBgUncheck
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.m10
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.theme.red600
import ru.aleksandra.core.theme.s20
import ru.aleksandra.core.theme.white
import ru.aleksandra.core.theme.xs10
import ru.aleksandra.core.ui.model.Recipient
import ru.aleksandra.core.ui.model.ShopWithItemForDelivery

@Composable
fun AvitoDeliveryPayment(
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    onOpenAllClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 22.dp),
    ) {
        Text(
            text = stringResource(Res.string.payment_method),
            style = MaterialTheme.typography.h30,
            color = MaterialTheme.colorScheme.contentPrimary,
        )
        Row(
            modifier = Modifier
                .padding(top = 27.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.image_bank_avito),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)

            )
            Column(
                modifier = Modifier
                    .padding(start = 13.dp, top = 4.dp)
                    .weight(1f)
            ) {
                Row() {
                    Text(
                        text = stringResource(Res.string.wallet),
                        style = MaterialTheme.typography.m20,
                        color = MaterialTheme.colorScheme.contentPrimary,
                    )
                    Box(
                        modifier = Modifier
                            .padding(start = 3.dp, top = 5.dp)
                            .background(
                                color = MaterialTheme.colorScheme.red600,
                                shape = RoundedCornerShape(48.dp)
                            )
                    ) {
                        Text(
                            text = stringResource(Res.string.minus_hundred_rubles),
                            style = MaterialTheme.typography.xs10,
                            color = MaterialTheme.colorScheme.white,
                            modifier = Modifier.padding(top = 1.dp, start = 4.dp, end = 4.dp)
                        )
                    }
                }
                Text(
                    text = stringResource(Res.string.quickly_top_up_via_sbp),
                    style = MaterialTheme.typography.s20,
                    color = MaterialTheme.colorScheme.contentSecondary,
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(CircleShape)
                    .size(24.dp)
                    .background(
                        if (selectedIndex == 0) MaterialTheme.colorScheme.controlBgCheck else MaterialTheme.colorScheme.controlBgUncheck
                    )
                    .clickable { onSelectedIndexChange(0) },
                contentAlignment = Alignment.Center
            ) {
                if (selectedIndex == 0) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_check),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.controlBgFaint,
                        modifier = Modifier.size(height = 11.39.dp, width = 15.11.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 22.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.image_bank_tbank),
                contentDescription = null,
                modifier = Modifier
                    .size(53.dp)

            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(Res.string.tbank),
                    style = MaterialTheme.typography.m20,
                    color = MaterialTheme.colorScheme.contentPrimary,
                )
                Text(
                    text = stringResource(Res.string.instantly_via_sbp),
                    style = MaterialTheme.typography.s20,
                    color = MaterialTheme.colorScheme.contentSecondary,
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(CircleShape)
                    .size(24.dp)
                    .background(
                        if (selectedIndex == 1) MaterialTheme.colorScheme.controlBgCheck else MaterialTheme.colorScheme.controlBgUncheck
                    )
                    .clickable { onSelectedIndexChange(1) },
                contentAlignment = Alignment.Center
            ) {
                if (selectedIndex == 1) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_check),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.controlBgFaint,
                        modifier = Modifier.size(height = 11.39.dp, width = 15.11.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 19.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.image_bank_sbp),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)

            )
            Column(
                modifier = Modifier
                    .padding(start = 13.dp, top = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(Res.string.sbp),
                    style = MaterialTheme.typography.m20,
                    color = MaterialTheme.colorScheme.contentPrimary,
                )
                Text(
                    text = stringResource(Res.string.pay_in_your_banks_app),
                    style = MaterialTheme.typography.s20,
                    color = MaterialTheme.colorScheme.contentSecondary,
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(CircleShape)
                    .size(24.dp)
                    .background(
                        if (selectedIndex == 2) MaterialTheme.colorScheme.controlBgCheck else MaterialTheme.colorScheme.controlBgUncheck
                    )
                    .clickable { onSelectedIndexChange(2) },
                contentAlignment = Alignment.Center
            ) {
                if (selectedIndex == 2) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_check),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.controlBgFaint,
                        modifier = Modifier.size(height = 11.39.dp, width = 15.11.dp)
                    )
                }
            }
        }
        Button(
            onClick = onOpenAllClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.controlBgDefault,
            ),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(top = 11.dp, bottom = 13.dp),
            modifier = Modifier
                .padding(top = 23.dp)
                .fillMaxWidth()
                .align (Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(Res.string.open_all),
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
        }
    }
}