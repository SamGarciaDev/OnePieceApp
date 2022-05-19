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

val OffWhite = Color(0xDEFFFFFF)
val LighterGrey = Color(0xFFF1F1F1)
val LightGrey = Color(0xFFBDBDBD)
val Grey = Color(0xFF707070)
val DarkGrey = Color(0xFF2A2A2A)
val DarkerGrey = Color(0xFF121212)

val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val Colors.onboardingScreenBackgroundColor
    @Composable
    get() = if (isLight) OffWhite else DarkerGrey

val Colors.onboardingScreenDescriptionColor
    @Composable
    get() = if (isLight) Grey else LightGrey

val Colors.homeTopBarBackgroundColor
    @Composable
    get() = if (isLight) Orange500 else Color.Black

val Colors.homeTopBarTextColor
    @Composable
    get() = if (isLight) Color.White else OffWhite