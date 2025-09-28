package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.ic_check
import org.jetbrains.compose.resources.painterResource
import ru.aleksandra.core.theme.controlBgFaint
import ru.aleksandra.core.theme.controlBgUncheck
import ru.aleksandra.core.theme.controlContentPrimary

@Composable
fun AvitoCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
        .padding(top = 1.dp, start = 1.dp, end = 2.dp, bottom = 2.dp),
    size: Dp = 18.dp,
    cornerRadius: Dp = 4.dp,
    checkedColor: Color = MaterialTheme.colorScheme.controlContentPrimary,
    uncheckedColor: Color = MaterialTheme.colorScheme.controlBgUncheck,
    checkmarkColor: Color = MaterialTheme.colorScheme.controlBgFaint
) {
    Box(
        modifier = modifier
            .size(size)
            .clickable { onCheckedChange(!checked) }
            .background(
                color = if (checked) checkedColor else uncheckedColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                painter = painterResource(Res.drawable.ic_check),
                contentDescription = null,
                tint = checkmarkColor,
                modifier = Modifier.size(20.dp)
                    .align(Alignment.Center)
                    .padding(vertical = 4.dp, horizontal = 3.dp)
            )
        }
    }
}