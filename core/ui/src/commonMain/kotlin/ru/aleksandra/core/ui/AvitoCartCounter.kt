package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.ic_check
import hackavito.core.ui.generated.resources.ic_minus
import hackavito.core.ui.generated.resources.ic_plus
import org.jetbrains.compose.resources.painterResource
import ru.aleksandra.core.theme.bgBase
import ru.aleksandra.core.theme.contentPrimary

@Composable
fun AvitoCartCounter(
    count: Int,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit,
    modifier: Modifier = Modifier.padding(top = 10.dp)
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.bgBase)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
//            Text(
//                text = "−",
//                modifier = Modifier
//                    .clickable { if (count > 0) onMinusClicked }
//                    .padding(start = 17.dp, end = 18.dp, top = 11.dp, bottom = 13.dp)
//            )
            Icon(
                painter = painterResource(Res.drawable.ic_minus),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier.padding(start = 14.dp, end = 18.dp, top = 11.dp, bottom = 13.dp)
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    .clickable{ onMinusClicked }
                    .padding(horizontal = 3.dp)
            )
            Text(
                text = count.toString(),
                textAlign = TextAlign.Center,
//                modifier = Modifier.weight(1f)
                modifier = Modifier.padding(top = 11.dp, bottom = 13.dp)
            )
            Icon(
                painter = painterResource(Res.drawable.ic_plus),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier.padding(start = 21.dp, end = 17.dp, top = 11.dp, bottom = 13.dp)
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    .clickable{ onPlusClicked }
                    .padding(horizontal = 3.dp)
            )
//            Text(
//                text = "+",
//                modifier = Modifier
//                    .clickable { onPlusClicked  }
//                    .padding(start = 18.dp, end = 17.dp, top = 11.dp, bottom = 13.dp)
//            )
        }
    }
}