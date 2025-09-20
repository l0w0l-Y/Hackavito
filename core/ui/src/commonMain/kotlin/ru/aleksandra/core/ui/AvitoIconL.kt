package ru.aleksandra.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import ru.aleksandra.core.theme.controlContentPrimary

@Composable
fun AvitoIconL(action: () -> Unit = {}) {
    IconButton(action, modifier = Modifier.size(24.dp)) {
        Icon(
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.controlContentPrimary
        )
    }
}