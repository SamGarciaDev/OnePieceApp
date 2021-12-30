package edu.samgarcia.onepieceapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Orange200 = Color(0xFFFFCC80)
val Orange300 = Color(0xFFFFB74D)
val Orange500 = Color(0xFFFF9800)
val Orange700 = Color(0xFFF57C00)

val Blue200 = Color(0xFF90CAF9)
val Blue500 = Color(0xFF2196F3)
val Blue700 = Color(0xFF1976D2)

val LightGrey = Color(0xFFBDBDBD)
val Grey = Color(0xFF707070)
val DarkGrey = Color(0xFF121212)

val OffWhite = Color(0xDEFFFFFF)

val Colors.onboardingScreenBackgroundColor
    @Composable
    get() = if(isLight) OffWhite else DarkGrey

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGrey else OffWhite

val Colors.descriptionColor
    @Composable
    get() = if (isLight) Grey else LightGrey