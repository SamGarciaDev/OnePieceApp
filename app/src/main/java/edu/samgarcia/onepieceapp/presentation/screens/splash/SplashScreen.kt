package edu.samgarcia.onepieceapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.motion.utils.Easing
import androidx.navigation.NavHostController
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.ui.theme.Orange500
import edu.samgarcia.onepieceapp.ui.theme.Orange700

@Composable
fun SplashScreen(navController: NavHostController) {
    val rotate = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        rotate.animateTo(
            targetValue = 45f,
            animationSpec = tween(
                durationMillis = 200,
                delayMillis = 500
            )
        )

        rotate.animateTo(
            targetValue = -45f,
            animationSpec = tween(durationMillis = 300),
        )

        rotate.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500)
        )
    }

    Splash(degrees = rotate.value)
}

@Composable
fun Splash(degrees: Float) {
    val brush: Brush = when {
        isSystemInDarkTheme() -> Brush.verticalGradient(listOf(Color.Black, Color.Black))
        else -> Brush.verticalGradient(listOf(Orange700, Orange500))
    }

    Box(
        modifier = Modifier
            .background(brush)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_one_piece_logo_thin),
            contentDescription = stringResource(R.string.app_logo_description),
            modifier = Modifier.size(125.dp).rotate(degrees = degrees)
        )

    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(0f)
}

@Preview (uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDark() {
    Splash(0f)
}