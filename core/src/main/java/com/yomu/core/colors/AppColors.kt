package com.yomu.core.colors

import androidx.compose.ui.graphics.Color

data class AppColors(
    val colorPrimary: Color,
    val colorOnPrimary: Color,
    val colorSecondary: Color,
    val colorSecondaryVariant: Color,
    val colorYomuNavigationBackground: Color,
    val colorYomuNavigationSelectedIcon: Color
)

val DefaultAppColors = AppColors(
    colorPrimary = Color(0xFF090808),
    colorOnPrimary = Color(0xFFFFFFFF),
    colorSecondary = Color(0xFF22BAA3),
    colorSecondaryVariant = Color(0xFF109581),
    colorYomuNavigationBackground = Color(0xFFEEFFFC),
    colorYomuNavigationSelectedIcon = Color(0xFFD1F1EB)
)