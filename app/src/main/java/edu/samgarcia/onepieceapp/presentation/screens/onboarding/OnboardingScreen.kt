package edu.samgarcia.onepieceapp.presentation.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.domain.model.OnboardingPage
import edu.samgarcia.onepieceapp.navigation.Screen
import edu.samgarcia.onepieceapp.ui.theme.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )

    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.onboardingScreenBackgroundColor)
    ) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.weight(8/12f)
        ) { page ->
            PagerScreen(onboardingPage = pages[page])
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = colors.primary,
            inactiveColor = Color.LightGray,
            spacing = XS_PADDING,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            modifier = Modifier.weight(2/12f)
        )

        FinishButton(
            pagerState = pagerState,
            modifier = Modifier.weight(2/12f)
        ) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            onboardingViewModel.saveOnboardingState(completed = true)
        }
    }
}

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter =  painterResource(
                id = if (isSystemInDarkTheme()) onboardingPage.imageDark else onboardingPage.image
            ),
            contentDescription = stringResource(R.string.onboarding_image_description),
            modifier = Modifier
                .size(L_IMAGE_SIZE)
                .padding(vertical = XL_PADDING)
        )

        Text(
            text = stringResource(onboardingPage.title),
            color = colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = MaterialTheme.typography.h4.fontWeight,
            modifier = Modifier.padding(bottom = S_PADDING)
        )

        Text(
            text = stringResource(onboardingPage.description),
            color = colors.descriptionColor,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = MaterialTheme.typography.subtitle1.fontWeight,
            modifier = Modifier.padding(horizontal = XL_PADDING)
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage == pagerState.pageCount - 1
        ) {
            Button(onClick = onClick) {
                Text(stringResource(id = R.string.finish_btn_text))
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun FirstOnboardingPagePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onboardingPage = OnboardingPage.First)
    }
}

@Preview (showBackground = true)
@Composable
fun SecondOnboardingPagePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onboardingPage = OnboardingPage.Second)
    }
}

@Preview (showBackground = true)
@Composable
fun ThirdOnboardingPagePreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PagerScreen(onboardingPage = OnboardingPage.Third)
    }
}