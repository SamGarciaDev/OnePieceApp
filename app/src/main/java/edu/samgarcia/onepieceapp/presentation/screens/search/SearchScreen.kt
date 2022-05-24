package edu.samgarcia.onepieceapp.presentation.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(it)
                },
                onSearchClicked = {},
                onCloseClicked = {
                    navHostController.popBackStack()
                }
            )
        }
    ) {

    }
}