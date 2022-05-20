package edu.samgarcia.onepieceapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.navigation.Screen
import edu.samgarcia.onepieceapp.presentation.components.RatingWidget
import edu.samgarcia.onepieceapp.presentation.components.ShimmerEffect
import edu.samgarcia.onepieceapp.ui.theme.CHARACTER_ITEM_HEIGHT
import edu.samgarcia.onepieceapp.ui.theme.S_PADDING
import edu.samgarcia.onepieceapp.ui.theme.XS_PADDING
import edu.samgarcia.onepieceapp.ui.theme.homeTopBarTextColor
import edu.samgarcia.onepieceapp.utils.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    characters: LazyPagingItems<OPCharacter>,
    navHostController: NavHostController
) {
    val result = handlePagingResult(characters = characters)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = XS_PADDING),
            verticalArrangement = Arrangement.spacedBy(XS_PADDING)
        ) {
            items(
                items = characters,
                key = { character -> character.id }
            ) { character ->
                character?.let {
                    CharacterItem(character = it, navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    characters: LazyPagingItems<OPCharacter>
): Boolean {
    characters.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error)
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CharacterItem(
    character: OPCharacter,
    navHostController: NavHostController
) {
    val painter = rememberImagePainter(data = "$BASE_URL${character.img}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(
        modifier = Modifier
            .height(CHARACTER_ITEM_HEIGHT)
            .clickable {
                navHostController.navigate(Screen.Details.passCharacterId(characterId = character.id))
            }
    ) {
        Surface(shape = RoundedCornerShape(size = S_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.character_image),
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                color = Color.Black.copy(alpha = ContentAlpha.medium),
                shape = RoundedCornerShape(
                    bottomStart = S_PADDING,
                    bottomEnd = S_PADDING
                )
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = S_PADDING)
                ) {
                    Text(
                        text = character.name,
                        color = MaterialTheme.colors.homeTopBarTextColor,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = XS_PADDING)
                    )

                    Text(
                        text = character.about,
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier.padding(top = XS_PADDING),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingWidget(
                            modifier = Modifier.padding(end = S_PADDING),
                            rating = character.rating
                        )

                        Text(
                            text = String.format("(%.1f)", character.rating),
                            textAlign = TextAlign.Center,
                            color = Color.White.copy(alpha = ContentAlpha.medium)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        character = OPCharacter(
            id = 1,
            name = "What",
            img = "",
            about = "Some random text...",
            rating = 3.6,
            devilFruits = listOf("Waht", "Ayo0"),
            family = listOf("What")
        ),
        navHostController = rememberNavController()
    )
}