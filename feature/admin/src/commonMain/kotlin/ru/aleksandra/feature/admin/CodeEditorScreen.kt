package ru.aleksandra.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hackavito.feature.admin.generated.resources.Res
import hackavito.feature.admin.generated.resources.ic_copy
import hackavito.feature.admin.generated.resources.ic_download
import hackavito.feature.admin.generated.resources.ic_repeat
import hackavito.feature.admin.generated.resources.ic_share
import hackavito.feature.admin.generated.resources.ic_upload
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import ru.aleksandra.core.sdui.domain.model.Shape
import ru.aleksandra.core.sdui.presentation.model.UIState
import ru.aleksandra.core.sdui.presentation.ui.Renderer
import ru.aleksandra.core.theme.bgPage
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.controlBgMasterPrimary
import ru.aleksandra.core.theme.controlBgPayPrimary
import ru.aleksandra.core.theme.controlContentMasterPassive
import ru.aleksandra.core.theme.controlContentPrimary
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.h50
import ru.aleksandra.core.theme.m20
import ru.aleksandra.feature.admin.model.AdminUIEffect

@Composable
fun CodeEditorScreen(
    adminViewModel: AdminViewModel = koinViewModel(),
) {
    var name by remember { mutableStateOf("") }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val screenSize = listOf(
        Pair(412, 917),
    )
    var selectedPlatform by remember { mutableStateOf(0) }
    val platformList = listOf<String>(
        "Android Compact", "Android Medium", "iOS 16", "iOS 13&14"
    )
    val targetList = listOf<String>(
        "Android", "iOS", "Web"
    )

    val state by adminViewModel.ui.collectAsState()
    val uiElements by adminViewModel.uiElements.collectAsState()
    val isCodeEditor by remember { mutableStateOf(true) }
    var isPreview by remember { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current
    var showDocumentation by remember { mutableStateOf(false) }
    var showType by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        adminViewModel.effect.collect {
            when (it) {
                is AdminUIEffect.UpdateJson -> {
                    val cursorPos = textFieldValue.selection.start
                    val newText = buildString {
                        append(textFieldValue.text.substring(0, cursorPos))
                        append(it.json)
                        append(textFieldValue.text.substring(cursorPos))
                    }
                    // курсор ставим после вставки
                    val newCursor = cursorPos + it.json.length

                    textFieldValue = textFieldValue.copy(
                        text = newText,
                        selection = TextRange(newCursor)
                    )
                }

                is AdminUIEffect.SetJson -> {
                    textFieldValue = textFieldValue.copy(
                        text = it.json,
                        selection = TextRange(0)
                    )
                }
            }
        }
    }
    Column() {
        Text(
            "Code Editor",
            style = MaterialTheme.typography.h30,
            color = MaterialTheme.colorScheme.controlContentPrimary
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Button(
                {
                    adminViewModel.openFile()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.bgPage
                )
            ) {
                Icon(
                    painterResource(Res.drawable.ic_download), contentDescription = null,
                    tint = MaterialTheme.colorScheme.contentPrimary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "Загрузить",
                    color = MaterialTheme.colorScheme.contentPrimary,
                    style = MaterialTheme.typography.m20
                )
            }
            Button(
                {
                    adminViewModel.saveToFile(name, textFieldValue.text)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.bgPage
                ),
            ) {
                Icon(
                    painterResource(Res.drawable.ic_upload), contentDescription = null,
                    tint = MaterialTheme.colorScheme.contentPrimary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "Сохранить", color = MaterialTheme.colorScheme.contentPrimary,
                    style = MaterialTheme.typography.m20
                )
            }
            Button(
                {
                    clipboardManager.setText(AnnotatedString(textFieldValue.text))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.bgPage
                ),
            ) {
                Icon(
                    painterResource(Res.drawable.ic_copy), contentDescription = null,
                    tint = MaterialTheme.colorScheme.contentPrimary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "Копировать", color = MaterialTheme.colorScheme.contentPrimary,
                    style = MaterialTheme.typography.m20
                )
            }
            Button(
                {
                    adminViewModel.loadFromJson(textFieldValue.text)
                    isPreview = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.controlBgMasterPrimary
                ),
            ) {
                Icon(
                    painterResource(Res.drawable.ic_repeat), contentDescription = null,
                    tint = MaterialTheme.colorScheme.bgPage
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "Обновить Preview", color = MaterialTheme.colorScheme.bgPage,
                    style = MaterialTheme.typography.m20
                )
            }
            Button(
                {
                    adminViewModel.sendToReview(textFieldValue.text)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.controlBgPayPrimary
                ),
            ) {
                Icon(
                    painterResource(Res.drawable.ic_share), contentDescription = null,
                    tint = MaterialTheme.colorScheme.controlContentMasterPassive
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "Отправить", color = MaterialTheme.colorScheme.controlContentMasterPassive,
                    style = MaterialTheme.typography.m20
                )
            }
        }
        var prompt by remember { mutableStateOf("") }
        Row(
            modifier = Modifier.padding(
                vertical = 12.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            OutlinedTextField(
                prompt,
                shape = RoundedCornerShape(12.dp),
                onValueChange = { prompt = it },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(
                        "Опишите, какой экран вы хотите сгенерировать",
                        style = MaterialTheme.typography.m20,
                        color = MaterialTheme.colorScheme.contentPrimary
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.contentPrimary
                )
            )
            Button(
                {
                    adminViewModel.getJsonByPrompt(prompt)
                    prompt = ""
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.controlBgPayPrimary
                ),
            ) {
                Text(
                    "Сейчас произойдет магия!",
                    color = MaterialTheme.colorScheme.controlContentMasterPassive,
                    style = MaterialTheme.typography.m20
                )
            }
        }
        Row(modifier = Modifier.fillMaxSize()) {
            if (isCodeEditor) {
                Row(modifier = Modifier.weight(1f)) {
                    CodeEditor(
                        textFieldValue = textFieldValue,
                        onTextChange = { textFieldValue = it },
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    ) {
                        stickyHeader {
                            Text(
                                "Элементы UI",
                                modifier = Modifier
                                    .background(Color.White)
                                    .clickable(onClick = { showType = !showType }),
                                style = MaterialTheme.typography.h50
                            )
                        }
                        if (showType) {
                            itemsIndexed(uiElements) { index, item ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable {
                                            selectedItem = index
                                            showDocumentation = true
                                        }
                                        .width(280.dp)
                                ) {
                                    Text(
                                        text = item.title,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(horizontal = 8.dp, vertical = 4.dp),
                                        style = MaterialTheme.typography.m20,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    IconButton({
                                        clipboardManager.setText(
                                            AnnotatedString(
                                                item.json
                                            )
                                        )
                                    }) {
                                        Icon(
                                            painterResource(Res.drawable.ic_copy),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.contentPrimary,
                                            modifier = Modifier
                                                .padding(4.dp)
                                        )
                                    }
                                    IconButton(
                                        {
                                            adminViewModel.addJson(index)
                                        }
                                    ) {
                                        Icon(
                                            painterResource(Res.drawable.ic_share),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.contentPrimary,
                                            modifier = Modifier
                                                .padding(4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    /*if (showDocumentation) {
                        Text(
                            uiElements.getOrNull(selectedItem)?.json ?: "Выберите элемент",
                            modifier = Modifier.width(120.dp)
                        )
                    }*/
                }
            }
            if (isPreview) {
                Column(
                    modifier = Modifier
                        .width(screenSize[0].first.dp)
                        .height(screenSize[0].second.dp)
                ) {
                    /*FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        platformList.forEachIndexed { index, str ->
                            FilterChip(
                                selected = selectedPlatform == index,
                                onClick = {
                                    selectedPlatform = index
                                },
                                label = {
                                    Text(str, style = MaterialTheme.typography.m20)
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.controlBgMasterPrimary,
                                    containerColor = MaterialTheme.colorScheme.controlBgDefault,
                                    labelColor = MaterialTheme.colorScheme.contentPrimary,
                                    selectedLabelColor = MaterialTheme.colorScheme.controlContentMasterPassive
                                ),
                                border = null,
                            )
                        }
                    }*/
                    //TODO: Добавить обработку action
                    when (state) {
                        is UIState.Init -> {
                            Text("Нажмите кнопку для загрузки")
                        }

                        is UIState.Error -> {
                            Text("Ошибка: ${(state as UIState.Error).message}")
                        }

                        is UIState.Loading -> {}
                        is UIState.Loaded -> {
                            Column(
                                modifier = Modifier
                                    .border(1.dp, Color.LightGray)
                            ) {
                                Renderer(uiState = state, handleAction = { _ -> })
                            }
                        }
                    }
                }
            }
        }
    }
}