package ru.aleksandra.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
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
    val scope = rememberCoroutineScope()

    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()

    /* LaunchedEffect(textFieldValue.text, textFieldValue.selection) {
         if (textFieldValue.text.endsWith("\n")) {
             // Если добавили новую строку → скроллим в начало
             scrollState.scrollTo(0)
         } else {
             // Скроллим к концу, когда курсор уходит вправо
             scrollState.scrollTo(scrollState.maxValue)
         }
     }*/

    Box(modifier = Modifier.weight(1f)) {
        Row(
            Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .horizontalScroll(horizontalScrollState)
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

            fun colorJson(input: String) = buildAnnotatedString {
                val stringRegex = Regex("\"(\\\\.|[^\"])*\"")
                val numberRegex = Regex("-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")
                val booleanRegex = Regex("\\b(true|false)\\b")
                val nullRegex = Regex("\\bnull\\b")
                val lineCommentRegex = Regex("//.*?(?=\n|$)")
                val blockCommentRegex = Regex("/\\*.*?\\*/")

                var i = 0
                while (i < input.length) {
                    val sub = input.substring(i)
                    lineCommentRegex.find(sub)?.takeIf { it.range.first == 0 }?.let {
                        withStyle(SpanStyle(color = Color.LightGray)) { append(it.value) } // зелёный-серый
                        i += it.value.length
                        return@let
                    } ?: run {
                        blockCommentRegex.find(sub)?.takeIf { it.range.first == 0 }?.let {
                            withStyle(SpanStyle(color = Color.LightGray)) { append(it.value) }
                            i += it.value.length
                            return@let
                        } ?: run {

                            // Строки
                            stringRegex.find(sub)?.takeIf { it.range.first == 0 }?.let {
                                val match = it.value
                                val color =
                                    if (sub.length > match.length && sub.getOrNull(match.length) == ':') Color(
                                        0xFFC179B5
                                    ) else Color(0xFF67A570)
                                withStyle(SpanStyle(color = color)) { append(match) }
                                i += match.length
                                return@let
                            } ?: run {
                                // Числа
                                numberRegex.find(sub)?.takeIf { it.range.first == 0 }?.let {
                                    withStyle(SpanStyle(color = Color(0xFF2CABB8))) { append(it.value) }
                                    i += it.value.length
                                    return@let
                                } ?: run {
                                    // Boolean
                                    booleanRegex.find(sub)?.takeIf { it.range.first == 0 }?.let {
                                        withStyle(SpanStyle(color = Color(0xFFDB8A60))) { append(it.value) }
                                        i += it.value.length
                                        return@let
                                    } ?: run {
                                        // Null
                                        nullRegex.find(sub)?.takeIf { it.range.first == 0 }?.let {
                                            withStyle(SpanStyle(color = Color(0xFFDB8A60))) {
                                                append(
                                                    it.value
                                                )
                                            }
                                            i += it.value.length
                                            return@let
                                        } ?: run {
                                            // Любой другой символ
                                            append(input[i])
                                            i++
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.gray4)
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp, vertical = 6.dp)
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
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        focusRequester.requestFocus()
                    }
            ) {
                Text(
                    text = colorJson(textFieldValue.text),
                    style = MaterialTheme.typography.m20,
                )
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { newValue ->
                        val oldText = textFieldValue.text
                        val newTextStr = newValue.text
                        val cursor = newValue.selection.start
                        val oldCursor = textFieldValue.selection.start
                        val newCursor = newValue.selection.start
                        val addedChar = newTextStr.getOrNull(cursor - 1)
                        val changeLength = newTextStr.length - oldText.length

                        if (changeLength > 0) {
                            when (addedChar) {
                                '\n' -> {
                                    val prevLineStart =
                                        oldText.lastIndexOf('\n', (cursor - 2).coerceAtLeast(0))
                                            .let { if (it == -1) 0 else it + 1 }

                                    val prevLineEnd = (cursor - 1).coerceAtLeast(prevLineStart)
                                    val prevLine = oldText.substring(prevLineStart, prevLineEnd)

                                    val leadingSpaces = prevLine.takeWhile { it == ' ' }

                                    val newTextWithIndent = newTextStr.substring(0, cursor) +
                                            leadingSpaces +
                                            newTextStr.substring(cursor)

                                    val newCursor = cursor + leadingSpaces.length

                                    onTextChange(
                                        newValue.copy(
                                            text = newTextWithIndent,
                                            selection = TextRange(newCursor)
                                        )
                                    )
                                }

                                '\t' -> {
                                    // Заменить tab на 4 пробела
                                    val newTextWithSpaces =
                                        newTextStr.substring(0, cursor - 1) +
                                                "    " +
                                                newTextStr.substring(cursor)
                                    onTextChange(
                                        newValue.copy(
                                            text = newTextWithSpaces,
                                            selection = TextRange(cursor + 3) // уже учтено 1 символ таб
                                        )
                                    )
                                }

                                else

                                    // В остальных случаях просто обновляем текст
                                    -> onTextChange(newValue)
                            }
                        } // Удаление символов
                        else if (changeLength < 0 && oldCursor > 0) {
                            // Ищем ближайший непробельный символ слева от курсора
                            var removeStart = oldCursor - 1
                            while (removeStart >= 0 && oldText[removeStart] == ' ') {
                                removeStart--
                            }
                            removeStart++ // первый пробел перед курсором

                            if (removeStart < oldCursor) {
                                val newTextWithoutSpaces =
                                    oldText.removeRange(removeStart, oldCursor)
                                onTextChange(
                                    TextFieldValue(
                                        text = newTextWithoutSpaces,
                                        selection = TextRange(removeStart)
                                    )
                                )
                            } else {
                                onTextChange(newValue)
                            }
                        } else {
                            // Без изменений (например, смена позиции курсора)
                            onTextChange(newValue)
                        }
                    },
                    textStyle = MaterialTheme.typography.m20.copy(Color.Transparent),
                    modifier = Modifier
                        .fillMaxSize()
                        .focusRequester(focusRequester),
                    onTextLayout = { layoutResult ->
                        val cursorIndex = textFieldValue.selection.end
                        val cursorRect = layoutResult.getCursorRect(cursorIndex)

                        scope.launch {
                            horizontalScrollState.scrollTo(cursorRect.left.toInt())
                            verticalScrollState.scrollTo(cursorRect.top.toInt())
                        }
                    }
                )
            }
        }
    }
}