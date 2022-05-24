package edu.samgarcia.onepieceapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import edu.samgarcia.onepieceapp.navigation.Screen
import edu.samgarcia.onepieceapp.presentation.common.ListContent

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allCharacters = homeViewModel.getAllCharacters.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navHostController.navigate(Screen.Search.route)
                }
            )
        },
        content = {
            ListContent(allCharacters, navHostController = navHostController)
        }
    )
}
