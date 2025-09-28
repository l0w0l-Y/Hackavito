package ru.aleksandra.core.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme()

val DarkColorScheme = darkColorScheme()

/*TODO: Добавить темную тему. Взяла все цвета, которые нашла в макете, если нужны другие, то добавлять их из списка цветов по имени по ссылке:
https://www.figma.com/design/tWqMXCBEc0AGoJ7YrW0pWe/001-Семантические-цвета?node-id=7865-29204&p=f */

val ColorScheme.contentPrimary: Color
    get() = Color(0xFF000000)

val ColorScheme.controlContentPrimary: Color
    get() = Color(0xFF000000)

val ColorScheme.bgPage: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.controlBgCheck: Color
    get() = Color(0xFF141414)

val ColorScheme.controlBgFaint: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.contentSecondary: Color
    get() = Color(0xFF757575)

val ColorScheme.controlContentError: Color
    get() = Color(0xFFFF4053)

val ColorScheme.bgBase: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.controlContentMasterPrimary: Color
    get() = Color(0xFF000000)

val ColorScheme.gray36: Color
    get() = Color(0xFFA3A3A3)

val ColorScheme.orange500: Color
    get() = Color(0xFFFFB021)

val ColorScheme.controlBgMasterDefault: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.otherLink: Color
    get() = Color(0xFF0099F7)

val ColorScheme.controlBgPayPrimary: Color
    get() = Color(0xFF965EEB)

val ColorScheme.controlBgDefault: Color
    get() = Color(0xFFF2F1F0)

val ColorScheme.gray4: Color
    get() = Color(0xFFF5F5F5)

val ColorScheme.controlContentPayPassive: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.controlContentMasterPassive: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.controlBgMasterPrimary: Color
    get() = Color(0xFF141414)

val ColorScheme.violet500: Color
    get() = Color(0xFFA168F7)

val ColorScheme.controlBgUncheck : Color
    get() = Color(0xFFFFFFFF)