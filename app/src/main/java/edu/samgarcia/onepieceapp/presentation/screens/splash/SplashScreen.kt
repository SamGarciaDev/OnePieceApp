package edu.samgarcia.onepieceapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.ui.theme.Orange500
import edu.samgarcia.onepieceapp.ui.theme.Orange700

@Composable
fun SplashScreen(navController: NavHostController) {
    Splash()
}

@Composable
fun Splash() {
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
            painter = painterResource(id = R.drawable.ic_one_piece_logo),
            contentDescription = stringResource(R.string.app_logo_description),
            modifier = Modifier.size(125.dp)
        )

    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash()
}

@Preview (uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDark() {
    Splash()
}