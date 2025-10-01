package ru.aleksandra.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import hackavito.core.theme.generated.resources.Manrope_Cut_008_ExtraBold
import hackavito.core.theme.generated.resources.Manrope_Cut_008_Medium
import hackavito.core.theme.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun setupFontFamily(): FontFamily {
    return FontFamily(
        Font(Res.font.Manrope_Cut_008_Medium, weight = FontWeight.Medium),
        Font(Res.font.Manrope_Cut_008_ExtraBold, weight = FontWeight.ExtraBold)
    )
}

@Composable
fun getDefaultTextStyle(): TextStyle {
    return TextStyle(
        fontFamily = setupFontFamily(),
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.contentPrimary,
    )
}

@Composable
fun setupTypography(): Typography {
    return Typography()
}

val Typography.h05: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 48.sp,
        lineHeight = 54.sp,
    )

val Typography.h10: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
        lineHeight = 36.sp,
    )

val Typography.h20: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 26.sp,
        lineHeight = 30.sp,
    )

val Typography.h25: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
    )

val Typography.xl10: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 26.sp,
    )

val Typography.h30: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 21.sp,
        lineHeight = 26.sp,
    )

val Typography.l10: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )

val Typography.l20: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    )

val Typography.h40: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    )

val Typography.h50: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    )

val Typography.m10: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 22.sp,
    )

val Typography.m20: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )

val Typography.h60: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )

val Typography.s10: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
    )

val Typography.s20: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 16.sp,
    )

val Typography.h70: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )

val Typography.xs10: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp,
    )

@Composable
fun String.toTextStyle(): TextStyle {
    return when (this) {
        "h05" -> MaterialTheme.typography.h05
        "h10" -> MaterialTheme.typography.h10
        "h20" -> MaterialTheme.typography.h20
        "h25" -> MaterialTheme.typography.h25
        "xl10" -> MaterialTheme.typography.xl10
        "h30" -> MaterialTheme.typography.h30
        "l10" -> MaterialTheme.typography.l10
        "l20" -> MaterialTheme.typography.l20
        "h40" -> MaterialTheme.typography.h40
        "h50" -> MaterialTheme.typography.h50
        "m10" -> MaterialTheme.typography.m10
        "m20" -> MaterialTheme.typography.m20
        "h60" -> MaterialTheme.typography.h60
        "s10" -> MaterialTheme.typography.s10
        "s20" -> MaterialTheme.typography.s20
        "h70" -> MaterialTheme.typography.h70
        "xs10" -> MaterialTheme.typography.xs10
        "bodySmall" -> MaterialTheme.typography.bodySmall
        "bodyMedium" -> MaterialTheme.typography.bodyMedium
        "bodyLarge" -> MaterialTheme.typography.bodyLarge
        "labelSmall" -> MaterialTheme.typography.labelSmall
        "labelMedium" -> MaterialTheme.typography.labelMedium
        "labelLarge" -> MaterialTheme.typography.labelLarge
        "titleSmall" -> MaterialTheme.typography.titleSmall
        "titleMedium" -> MaterialTheme.typography.titleMedium
        "titleLarge" -> MaterialTheme.typography.titleLarge
        "displaySmall" -> MaterialTheme.typography.displaySmall
        "displayMedium" -> MaterialTheme.typography.displayMedium
        "displayLarge" -> MaterialTheme.typography.displayLarge
        else -> MaterialTheme.typography.m20
    }
}