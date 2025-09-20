package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.navbar_cart
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.controlContentPrimary
import ru.aleksandra.core.theme.m10

@Composable
fun AvitoNavBar() {
    Row(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvitoIconL()
        Text(
            "Выбрать все",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.m10,
            color = MaterialTheme.colorScheme.controlContentPrimary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(24.dp))
    }
}