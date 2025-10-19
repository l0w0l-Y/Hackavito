package ru.aleksandra.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme()

val DarkColorScheme = darkColorScheme()

/*TODO: Добавить темную тему. Взяла все цвета, которые нашла в макете, если нужны другие, то добавлять их из списка цветов по имени по ссылке:
https://www.figma.com/design/tWqMXCBEc0AGoJ7YrW0pWe/001-Семантические-цвета?node-id=7865-29204&p=f */

val ColorScheme.contentPrimary: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFFFFFFF)

val ColorScheme.controlContentPrimary: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFFFFFFF)

val ColorScheme.bgPage: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF000000)

val ColorScheme.controlBgCheck: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF141414) else Color(0xFFFFFFFF)

val ColorScheme.controlBgFaint: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF1E1E1E)

val ColorScheme.contentSecondary: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF757575) else Color(0xFFA3A3A3)

val ColorScheme.controlContentError: Color
    get() = Color(0xFFFF4053)

val ColorScheme.bgBase: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF121212)

val ColorScheme.controlContentMasterPrimary: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFFFFFFF)

val ColorScheme.gray36: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFA3A3A3) else Color(0xFF757575)

val ColorScheme.orange500: Color
    get() = Color(0xFFFFB021)

val ColorScheme.controlBgMasterDefault: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF1E1E1E)

val ColorScheme.otherLink: Color
    get() = Color(0xFF0099F7)

val ColorScheme.controlBgPayPrimary: Color
    get() = Color(0xFF965EEB)

val ColorScheme.controlBgDefault: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFF2F1F0) else Color(0xFF2C2C2C)

val ColorScheme.gray4: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFF5F5F5) else Color(0xFF262624)

val ColorScheme.gray54: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF757575) else Color(0xFFA3A3A3)

val ColorScheme.red600: Color
    get() = Color(0xFFFF4053)

val ColorScheme.green800: Color
    get() = Color(0xFF00B253)

val ColorScheme.white: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.controlContentPayPassive: Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.controlContentMasterPassive: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF141414)

val ColorScheme.controlBgMasterPrimary: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF141414) else Color(0xFFFFFFFF)

val ColorScheme.violet500: Color
    get() = Color(0xFFA168F7)

val ColorScheme.controlBgUncheck: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFE3E2E1) else Color(0xFF2A2A2A)

val ColorScheme.dayBlack: Color
    get() = Color(0xFF000000)

val ColorScheme.warmgray4: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFF2F1F0) else Color(0xFF1C1C1C)

val ColorScheme.black: Color
    get() = Color(0xFF000000)

val ColorScheme.gray92: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF141414) else Color(0xFFF2F1F0)

val ColorScheme.buttonTextSecondary : Color
    get() = Color(0xFF000000)

val ColorScheme.buttonTextPrimary : Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.controlTextPrimary : Color
    get() = Color(0xFF000000)

val ColorScheme.controlTextSecondary : Color
    get() = Color(0xFF757575)

val ColorScheme.buttonBgPrimary : Color
    get() = Color(0xFF141414)

val ColorScheme.bgElevation2 : Color
    get() = Color(0xFFFFFFFF)

val ColorScheme.bgElevation1 : Color
    get() = Color(0xFFFFFFFF)

// Эх, грустим, что в kmp нет рефлексии и добавляем сюда все новые цвета :(
@Composable
fun String.toThemeColor(): Color {
    return when (this) {
        "primary" -> MaterialTheme.colorScheme.primary
        "onPrimary" -> MaterialTheme.colorScheme.onPrimary
        "primaryContainer" -> MaterialTheme.colorScheme.primaryContainer
        "onPrimaryContainer" -> MaterialTheme.colorScheme.onPrimaryContainer
        "secondary" -> MaterialTheme.colorScheme.secondary
        "onSecondary" -> MaterialTheme.colorScheme.onSecondary
        "secondaryContainer" -> MaterialTheme.colorScheme.secondaryContainer
        "onSecondaryContainer" -> MaterialTheme.colorScheme.onSecondaryContainer
        "tertiary" -> MaterialTheme.colorScheme.tertiary
        "onTertiary" -> MaterialTheme.colorScheme.onTertiary
        "tertiaryContainer" -> MaterialTheme.colorScheme.tertiaryContainer
        "onTertiaryContainer" -> MaterialTheme.colorScheme.onTertiaryContainer
        "error" -> MaterialTheme.colorScheme.error
        "onError" -> MaterialTheme.colorScheme.onError
        "errorContainer" -> MaterialTheme.colorScheme.errorContainer
        "onErrorContainer" -> MaterialTheme.colorScheme.onErrorContainer
        "background" -> MaterialTheme.colorScheme.background
        "onBackground" -> MaterialTheme.colorScheme.onBackground
        "surface" -> MaterialTheme.colorScheme.surface
        "onSurface" -> MaterialTheme.colorScheme.onSurface
        "surfaceVariant" -> MaterialTheme.colorScheme.surfaceVariant
        "onSurfaceVariant" -> MaterialTheme.colorScheme.onSurfaceVariant
        "outline" -> MaterialTheme.colorScheme.outline
        "inverseOnSurface" -> MaterialTheme.colorScheme.inverseOnSurface
        "inverseSurface" -> MaterialTheme.colorScheme.inverseSurface
        "inversePrimary" -> MaterialTheme.colorScheme.inversePrimary
        "surfaceTint" -> MaterialTheme.colorScheme.surfaceTint
        //Добавь еще из color модуля core-theme
        "contentPrimary" -> MaterialTheme.colorScheme.contentPrimary
        "controlContentPrimary" -> MaterialTheme.colorScheme.controlContentPrimary
        "bgPage" -> MaterialTheme.colorScheme.bgPage
        "controlBgCheck" -> MaterialTheme.colorScheme.controlBgCheck
        "controlBgFaint" -> MaterialTheme.colorScheme.controlBgFaint
        "contentSecondary" -> MaterialTheme.colorScheme.contentSecondary
        "controlContentError" -> MaterialTheme.colorScheme.controlContentError
        "bgBase" -> MaterialTheme.colorScheme.bgBase
        "controlContentMasterPrimary" -> MaterialTheme.colorScheme.controlContentMasterPrimary
        "gray36" -> MaterialTheme.colorScheme.gray36
        "orange500" -> MaterialTheme.colorScheme.orange500
        "controlBgMasterDefault" -> MaterialTheme.colorScheme.controlBgMasterDefault
        "otherLink" -> MaterialTheme.colorScheme.otherLink
        "controlBgPayPrimary" -> MaterialTheme.colorScheme.controlBgPayPrimary
        "controlBgDefault" -> MaterialTheme.colorScheme.controlBgDefault
        "gray4" -> MaterialTheme.colorScheme.gray4
        "controlContentPayPassive" -> MaterialTheme.colorScheme.controlContentPayPassive
        "controlContentMasterPassive" -> MaterialTheme.colorScheme.controlContentMasterPassive
        "controlBgMasterPrimary" -> MaterialTheme.colorScheme.controlBgMasterPrimary
        "violet500" -> MaterialTheme.colorScheme.violet500
        "controlBgUncheck" -> MaterialTheme.colorScheme.controlBgUncheck
        else -> Color.Unspecified
    }
}