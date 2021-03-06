package edu.samgarcia.onepieceapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import edu.samgarcia.onepieceapp.presentation.screens.details.DetailsScreen
import edu.samgarcia.onepieceapp.presentation.screens.home.HomeScreen
import edu.samgarcia.onepieceapp.presentation.screens.onboarding.OnboardingScreen
import edu.samgarcia.onepieceapp.presentation.screens.search.SearchScreen
import edu.samgarcia.onepieceapp.presentation.screens.splash.SplashScreen
import edu.samgarcia.onepieceapp.utils.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) { type = NavType.IntType})
        ) {
            DetailsScreen(navController)
        }

        composable(route = Screen.Search.route) {
            SearchScreen(navController)
        }
    }
}