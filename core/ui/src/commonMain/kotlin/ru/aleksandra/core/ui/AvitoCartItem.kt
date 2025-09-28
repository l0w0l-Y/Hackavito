package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.amount_with_ruble
import hackavito.core.ui.generated.resources.buy_with_delivery
import hackavito.core.ui.generated.resources.ic_delete
import hackavito.core.ui.generated.resources.ic_favorite
import hackavito.core.ui.generated.resources.sale_with_percent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.bgBase
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlContentError
import ru.aleksandra.core.theme.h40
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.theme.s10
import ru.aleksandra.core.theme.s20
import ru.aleksandra.core.theme.violet500

@Composable
fun AvitoCartItem(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    name: String,
    priceWithoutDiscount: Int,
    priceWithDiscount: Int,
    salePercent: Int,
    count: Int,
    image: String,
    onPlusItemCountClicked: () -> Unit,
    onMinusItemCountClicked: () -> Unit,
) {
    HorizontalDivider(
        color = Color.Black, // цвет линии
        thickness = 1.dp,   // толщина линии
        modifier = Modifier.fillMaxWidth() // растягиваем по ширине
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.bgBase)
            .padding(horizontal = 16.dp),
    ) {
        Box(
            Modifier.size(21.dp, 21.dp)
        ) {
            AvitoCheckbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
            )
        }
//        Box(
//            modifier = Modifier
//                .padding(start = 12.dp)
//                .size(96.dp) // квадрат
//                .clip(shape = RoundedCornerShape(8.dp)) // скругление углов
//        ){
//
//        }
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 12.dp)
                .size(96.dp) // квадрат
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Text(
                text = stringResource(
                    Res.string.amount_with_ruble,
                    if (salePercent < 1 && salePercent > 100) priceWithoutDiscount else priceWithDiscount
                ),
                style = MaterialTheme.typography.h40,
                color = MaterialTheme.colorScheme.contentPrimary,
            )
            if (salePercent > 0 && salePercent < 100) {
                Row {
                    Text(
                        stringResource(
                            Res.string.amount_with_ruble,
                            priceWithoutDiscount.toInt()
                        ),
                        style = MaterialTheme.typography.m20,
                        color = MaterialTheme.colorScheme.contentPrimary,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        stringResource(Res.string.sale_with_percent, salePercent),
                        style = MaterialTheme.typography.m20,
                        color = MaterialTheme.colorScheme.controlContentError,
                    )
                }
            }
            Text(
                name,
                style = MaterialTheme.typography.s20,
                color = MaterialTheme.colorScheme.contentPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 2.dp)
            )
            AvitoCartCounter(
                count = count,
                onPlusClicked = onPlusItemCountClicked,
                onMinusClicked = onMinusItemCountClicked
            )
            Text(
                text = stringResource(Res.string.buy_with_delivery),
                style = MaterialTheme.typography.s10,
                color = MaterialTheme.colorScheme.violet500,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 10.dp)

            )
        }
        Column(
            modifier = Modifier.align(alignment = Alignment.Top)
                .width(28.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_favorite),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .size(28.dp)
                    .padding(all = 4.dp)
            )
            Icon(
                painter = painterResource(Res.drawable.ic_delete),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentPrimary,
                modifier = Modifier
                    .size(28.dp)
                    .padding(all = 4.dp)
            )
        }

    }
    Divider(
        color = Color.Black, // цвет линии
        thickness = 1.dp,   // толщина линии
        modifier = Modifier.fillMaxWidth() // растягиваем по ширине
    )
}