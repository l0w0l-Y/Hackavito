package ru.aleksandra.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.ic_star
import org.jetbrains.compose.resources.painterResource
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.gray36
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.m10
import ru.aleksandra.core.theme.orange500
import kotlin.math.floor

@Composable
fun AvitoShopName(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    shopName: String,
    rating: Float,
    reviewsCount: Int
) {
//    Spacer(modifier = Modifier.height(200.dp))
//    Divider(
//        color = Color.Black, // цвет линии
//        thickness = 1.dp,   // толщина линии
//        modifier = Modifier.fillMaxWidth() // растягиваем по ширине
//    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Box(
            Modifier.padding(top = 2.5.dp, bottom = 2.5.dp)
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
                .padding(start = 11.dp)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                text = shopName,
                style = MaterialTheme.typography.h30,
                color = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier.align(Alignment.Bottom)
            )
//            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = 4.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.orange500,
                    modifier = Modifier.padding(vertical = 3.dp)
                        .size(16.dp)
                        .align(Alignment.CenterVertically)

                )
                Text(
                    text = rating.toOneDecimalString(),
                    style = MaterialTheme.typography.m10,
                    color = MaterialTheme.colorScheme.contentPrimary,
                    modifier = Modifier.padding(horizontal = 2.dp)
                        .align(Alignment.CenterVertically)

                )
                Text(
                    text = "($reviewsCount)",
                    style = MaterialTheme.typography.m10,
                    color = MaterialTheme.colorScheme.gray36,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

    }
//    Divider(
//        color = Color.Black, // цвет линии
//        thickness = 1.dp,   // толщина линии
//        modifier = Modifier.fillMaxWidth() // растягиваем по ширине
//    )
}

fun Float.toOneDecimalString(): String {
    val truncated = floor(this * 10f) / 10f
    return if (truncated % 1f == 0f) {
        "${truncated.toInt()},0"
    } else {
        truncated.toString().replace('.', ',')
    }
}