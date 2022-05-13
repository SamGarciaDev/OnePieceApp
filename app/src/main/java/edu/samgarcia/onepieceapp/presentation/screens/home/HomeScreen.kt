package edu.samgarcia.onepieceapp.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import edu.samgarcia.onepieceapp.presentation.components.RatingWidget
import edu.samgarcia.onepieceapp.ui.theme.L_PADDING

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allCharacters = homeViewModel.getAllCharacters.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = { })
        }
    ) {
        RatingWidget(modifier = Modifier.padding(all = L_PADDING), rating = 3.6)
    }
}
