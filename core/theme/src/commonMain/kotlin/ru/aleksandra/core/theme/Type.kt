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
        Font(Res.font.Manrope_Cut_008_Medium, weight = FontWeight.Normal),
        Font(Res.font.Manrope_Cut_008_ExtraBold, weight = FontWeight.ExtraBold)
    )
}

@Composable
fun getDefaultTextStyle(): TextStyle {
    return TextStyle(
        fontFamily = setupFontFamily(),
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
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
        fontWeight = FontWeight.Normal,
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
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )

val Typography.l20: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Normal,
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
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
    )

val Typography.m20: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Normal,
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
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
    )

val Typography.s20: TextStyle
    @Composable
    get() = getDefaultTextStyle().copy(
        fontWeight = FontWeight.Normal,
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
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 14.sp,
    )
