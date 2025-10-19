package ru.aleksandra.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.delivery_methods
import hackavito.core.ui.generated.resources.delivery_registration
import hackavito.core.ui.generated.resources.ic_cross
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.controlContentPrimary
import ru.aleksandra.core.theme.h50

@Composable
fun AvitoDeliveryAddressesNavBar(
    action: () -> Unit = {}
) {
    Row(
        modifier = Modifier
//            .safeDrawingPadding()
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(action, modifier = Modifier.size(24.dp)) {
            Icon(
                painter = painterResource(Res.drawable.ic_cross),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.controlContentPrimary
            )
        }
        Text(
            text = stringResource(Res.string.delivery_methods),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h50,
            color = MaterialTheme.colorScheme.controlContentPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(24.dp))
    }
}