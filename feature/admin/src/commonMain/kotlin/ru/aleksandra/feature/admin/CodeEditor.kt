package ru.aleksandra.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.aleksandra.core.theme.gray36
import ru.aleksandra.core.theme.gray4
import ru.aleksandra.core.theme.m20

@Composable
fun RowScope.CodeEditor(
    textFieldValue: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    errorLines: List<Int> = emptyList(),
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    /* LaunchedEffect(textFieldValue.text, textFieldValue.selection) {
         if (textFieldValue.text.endsWith("\n")) {
             // Если добавили новую строку → скроллим в начало
             scrollState.scrollTo(0)
         } else {
             // Скроллим к концу, когда курсор уходит вправо
             scrollState.scrollTo(scrollState.maxValue)
         }
     }*/

    Row(
        Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.gray4)
    ) {
        val lines = textFieldValue.text.split("\n")

        /*val annotatedText = if (errorLines.isNotEmpty()) {
            buildAnnotatedString {
                lines.forEachIndexed { index, line ->
                    val start = length
                    append(line)
                    val end = length
                    if (errorLines.contains(index + 1)) {
                        addStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                color = Color.Red
                            ),
                            start = start,
                            end = end
                        )
                    }
                    append("\n")
                }
            }
        } else {
            AnnotatedString(text.text)
        }*/

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.gray4)
                .fillMaxHeight()
                .padding(horizontal = 4.dp)
        ) {
            lines.indices.forEach { i ->
                Text(
                    text = (i + 1).toString(),
                    color = MaterialTheme.colorScheme.gray36,
                    style = MaterialTheme.typography.m20,
                )
            }
        }

        val focusRequester = remember { FocusRequester() }
        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxSize()
                .horizontalScroll(scrollState)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    focusRequester.requestFocus()
                }
        ) {
            BasicTextField(
                value = textFieldValue,
                onValueChange = { onTextChange(it) },
                textStyle = MaterialTheme.typography.m20,
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester),
                onTextLayout = { layoutResult ->
                    val cursorIndex = textFieldValue.selection.end
                    val cursorRect = layoutResult.getCursorRect(cursorIndex)
                    val visibleWidth = layoutResult.size.width

                    // вычисляем желаемый scroll: курсор виден, но начало строки по возможности
                    val newScroll = cursorRect.right - visibleWidth

                    scope.launch {
                        scrollState.scrollTo(newScroll.toInt())
                    }
                }
            )
        }
    }
}

@Composable
fun TextUnit.toDp(): Dp {
    return with(LocalDensity.current) { this@toDp.toDp() }
}
