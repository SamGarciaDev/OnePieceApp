package edu.samgarcia.onepieceapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = { })
        }
    ) {

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
