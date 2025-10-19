package ru.aleksandra.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.amount_with_ruble
import hackavito.core.ui.generated.resources.image_bank_avito
import hackavito.core.ui.generated.resources.image_bank_sbp
import hackavito.core.ui.generated.resources.image_bank_tbank
import hackavito.core.ui.generated.resources.proceed_to_payment
import hackavito.core.ui.generated.resources.total_with_delivery
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.bgPage
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlBgMasterPrimary
import ru.aleksandra.core.theme.controlContentMasterPassive
import ru.aleksandra.core.theme.h50
import ru.aleksandra.core.theme.m20

@Composable
fun AvitoDeliveryTotal(
//    selectedIndex: MutableState<Int>,
    selectedIndex: Int,
    totalPrice: Int,
    onProceedToPaymentClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.bgPage, shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .padding(start = 16.dp, top = 20.dp, end = 16.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(Res.string.total_with_delivery),
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
            Text(
                text = stringResource(Res.string.amount_with_ruble, totalPrice),
                style = MaterialTheme.typography.h50,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            Image(
                painter = painterResource(
//                    when (selectedIndex.value) {
                    when (selectedIndex) {
                        1 -> Res.drawable.image_bank_tbank
                        2 -> Res.drawable.image_bank_sbp
                        else -> Res.drawable.image_bank_avito
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 6.dp)
                    .size(52.dp)

            )
            Button(
                onClick = onProceedToPaymentClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.controlBgMasterPrimary,
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(top = 15.dp, bottom = 17.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.proceed_to_payment),
                    style = MaterialTheme.typography.m20,
                    color = MaterialTheme.colorScheme.controlContentMasterPassive,
                )
            }
        }
    }
}