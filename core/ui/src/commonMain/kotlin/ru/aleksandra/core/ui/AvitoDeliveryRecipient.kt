package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.change_recipient
import hackavito.core.ui.generated.resources.recipient
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.contentSecondary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.m10
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.ui.model.Recipient
import ru.aleksandra.core.ui.model.ShopWithItemForDelivery

@Composable
fun AvitoDeliveryRecipient(
    recipient: Recipient,
    onChangeRecipientClick : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),
    ) {
        Text(
            text = stringResource(Res.string.recipient),
            style = MaterialTheme.typography.h30,
            color = MaterialTheme.colorScheme.contentPrimary,
        )
        Text(
            text = recipient.name,
            style = MaterialTheme.typography.m10,
            color = MaterialTheme.colorScheme.contentPrimary,
            modifier = Modifier.padding(top = 12.dp)
        )
        Row(
            modifier = Modifier.padding(top = 2.dp)
        ) {
            Text(
                text = recipient.number,
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = "·",
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentSecondary,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = recipient.mail,
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
        }
        Button(
            onClick = onChangeRecipientClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.controlBgDefault,
            ),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(top = 11.dp, bottom = 13.dp, start = 16.dp, end = 17.dp),
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            Text(
                text = stringResource(Res.string.change_recipient),
                style = MaterialTheme.typography.m20,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
        }

    }
}