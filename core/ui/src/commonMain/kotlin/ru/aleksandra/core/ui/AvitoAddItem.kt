package ru.aleksandra.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import hackavito.core.ui.generated.resources.add_discounted_item_before_sale
import hackavito.core.ui.generated.resources.delete
import hackavito.core.ui.generated.resources.ic_arrow
import hackavito.core.ui.generated.resources.ic_plus
import hackavito.core.ui.generated.resources.ic_pointer
import hackavito.core.ui.generated.resources.select_all
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.h60
import ru.aleksandra.core.theme.m10
import ru.aleksandra.core.theme.otherLink
import ru.aleksandra.core.theme.s20

@Composable
fun AvitoAddItem(
    itemsBeforeDiscountCount: Int, //кол-во товаров до скидки
    salePercent: Int, //процент скидки

){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_arrow),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.contentPrimary,
            modifier = Modifier.size(24.dp)
                .align(Alignment.Top)
            )

        Column (
            modifier = Modifier.padding(start = 6.dp)
        ){
            Row (
                modifier = Modifier.padding(top = 4.dp)
            ){
                Text(
                    text = stringResource(Res.string.add_discounted_item_before_sale, itemsBeforeDiscountCount, salePercent),
                    style = MaterialTheme.typography.h60,
                )
                Icon(
                    painter = painterResource(Res.drawable.ic_pointer),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.contentPrimary,
                    modifier = Modifier.padding()
                        .size(16.dp)
                )

            }
        }
    }

}