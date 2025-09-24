package ru.aleksandra.feature.admin

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import ru.aleksandra.core.sdui.presentation.model.UIState
import ru.aleksandra.core.sdui.presentation.ui.Renderer

@Composable
fun AdminApp(
    adminViewModel: AdminViewModel = koinViewModel(),
) {
    var name by remember { mutableStateOf("") }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val screenSize = listOf(
        Pair(700, 840),
        Pair(412, 917),
        Pair(393, 852),
        Pair(390, 844),
    )
    val targetList = listOf<String>(
        "Android", "iOS", "Web"
    )
    var type by remember { mutableStateOf(0) }

    val state by adminViewModel.ui.collectAsState()
    val uiElements by adminViewModel.uiElements.collectAsState()

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
            }
        }
    }

    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            TextField(
                value = name,
                onValueChange = { name = it },
            )
            FlowRow {
                Button({
                    type = 0
                }) {
                    Text("Android Compact")
                }
                Button({
                    type = 1
                }) {
                    Text("Android Medium")
                }
                Button({
                    type = 2
                }) {
                    Text("Iphone 16")
                }
                Button({
                    type = 3
                }) {
                    Text("Iphone 13&14")
                }
            }
            Row(modifier = Modifier.weight(1f)) {
                CodeEditor(
                    textFieldValue = textFieldValue,
                    onTextChange = { textFieldValue = it },
                )
                Column {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(uiElements) { index, item ->
                            Button({
                                adminViewModel.addJson(index)
                            }) {
                                Text(item)
                            }
                        }
                    }
                }
            }
            Button({ adminViewModel.loadFromJson(textFieldValue.text) }) {
                Text("Отобразить экран")
            }
            Button(
                { adminViewModel.saveToFile(name, textFieldValue.text) }
            ) {
                Text("Сохранить экран")
            }
            Button(
                {
                    adminViewModel.openFile()
                }
            ) {
                Text("Загрузить экран")
            }
        }
        Column(modifier = Modifier.weight(2f)) {
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
                            .width(screenSize[type].first.dp)
                            .height(screenSize[type].second.dp)
                    ) {
                        Renderer(uiState = state, handleAction = { _ -> })
                    }
                }
            }
        }
    }
}