package ru.aleksandra.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.m10
import ru.aleksandra.core.theme.otherLink

@Composable
fun AvitoSelectAll(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
    deleteCount: Int,
    onDeleteClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Box(
            Modifier.padding(top = 12.dp, bottom = 9.dp)
                .size(21.dp, 21.dp)
        ) {
            AvitoCheckbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 11.dp, top = 12.dp, bottom = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                text = "Выбрать всё",
                style = MaterialTheme.typography.m10,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Удалить ($deleteCount)",
                style = MaterialTheme.typography.m10,
                color = MaterialTheme.colorScheme.otherLink,
                modifier = Modifier.clickable { onDeleteClick() }
            )
        }
    }
}