package edu.samgarcia.onepieceapp.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Onboarding: Screen("onboarding_screen")
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object Details: Screen("details_screen/{heroId}") {
        fun passHeroId(characterId: Int): String {
            return "details_screen/$characterId"
        }
    }
}
