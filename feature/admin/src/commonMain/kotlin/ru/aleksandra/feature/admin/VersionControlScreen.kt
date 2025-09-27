package ru.aleksandra.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import hackavito.feature.admin.generated.resources.Res
import hackavito.feature.admin.generated.resources.ic_person
import hackavito.feature.admin.generated.resources.ic_time
import org.jetbrains.compose.resources.painterResource
import ru.aleksandra.core.theme.contentSecondary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.controlBgMasterPrimary
import ru.aleksandra.core.theme.controlContentMasterPassive
import ru.aleksandra.core.theme.controlContentPrimary
import ru.aleksandra.core.theme.h10
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.m10
import ru.aleksandra.core.theme.m20
import ru.aleksandra.feature.admin.model.ScreenVersionControlModel
import ru.aleksandra.feature.admin.model.VersionControl

@Composable
fun VersionControlScreen() {
    val screen = ScreenVersionControlModel(
        screen = ru.aleksandra.feature.admin.model.Screen(
            id = "1",
            name = "Cart",
            description = "Главный экран приложения, где пользователь делает покупки",
            screenImageUrl = null
        ),
        listOf(
            VersionControl(
                id = "1",
                version = "v1.0",
                createdAt = "16 минут назад",
                authorId = "admin",
                commit = "Первая версия экрана, добавлена корзина и возможность удалять товары",
                jsonContent = """{"type":"AvitoNavBar","title":""}""",
                isDefault = true
            ),
            VersionControl(
                id = "2",
                version = "v1.1",
                createdAt = "2 часа назад",
                authorId = "developer1",
                commit = "Добавлена возможность применять промокоды и скидки",
                jsonContent = "{}",
                isDefault = false
            ),
            VersionControl(
                id = "3",
                version = "v1.2",
                createdAt = "24 сентября",
                authorId = "developer2",
                commit = "Оптимизирована производительность и исправлены баги с отображением товаров",
                jsonContent = "{}",
                isDefault = false
            )
        )
    )
    var selectedVersionId by remember { mutableStateOf(0) }

    Column {
        Text(
            "Version Control",
            style = MaterialTheme.typography.h30,
            color = MaterialTheme.colorScheme.controlContentPrimary
        )
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    screen.screen.name,
                    style = MaterialTheme.typography.h10,
                )
                Text(
                    text = "${screen.versions.size} версии",
                    style = MaterialTheme.typography.m20,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            MaterialTheme.colorScheme.controlBgMasterPrimary
                        )
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.controlContentMasterPassive
                )
            }
            Text(
                screen.screen.description,
                style = MaterialTheme.typography.m20,
            )
            //Image(painter = painterResource(Res.drawable.cartscreen), null)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_time),
                        null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Доступные версии:", style = MaterialTheme.typography.h30)
                }
                screen.versions.forEachIndexed { index, it ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                selectedVersionId = index
                            }
                            .padding(all = 6.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(it.commit, style = MaterialTheme.typography.m10)
                            Row {
                                Icon(
                                    painter = painterResource(
                                        Res.drawable.ic_person,
                                    ),
                                    null,
                                    tint = MaterialTheme.colorScheme.contentSecondary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    it.authorId, style = MaterialTheme.typography.m20,
                                    color = MaterialTheme.colorScheme.contentSecondary
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    it.createdAt,
                                    color = MaterialTheme.colorScheme.contentSecondary
                                )
                            }
                        }
                        Text(
                            it.version,
                            style = MaterialTheme.typography.m20,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (it.isDefault) MaterialTheme.colorScheme.controlBgMasterPrimary else MaterialTheme.colorScheme.controlBgDefault
                                )
                                .padding(horizontal = 6.dp, vertical = 4.dp),
                            color = if (it.isDefault) MaterialTheme.colorScheme.controlContentMasterPassive else MaterialTheme.colorScheme.controlContentPrimary
                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    screen.versions[selectedVersionId].jsonContent,
                    style = MaterialTheme.typography.m20,
                )
            }
        }
    }
}