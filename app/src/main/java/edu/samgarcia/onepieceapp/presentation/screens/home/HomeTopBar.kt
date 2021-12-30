package edu.samgarcia.onepieceapp.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.ui.theme.homeTopBarBackgroundColor
import edu.samgarcia.onepieceapp.ui.theme.homeTopBarTextColor

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.homeTopBarTextColor
            )
        },
        backgroundColor = MaterialTheme.colors.homeTopBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colors.homeTopBarTextColor,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar {}
}