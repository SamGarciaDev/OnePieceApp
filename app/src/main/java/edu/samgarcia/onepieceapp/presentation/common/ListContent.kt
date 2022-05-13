package edu.samgarcia.onepieceapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.navigation.Screen
import edu.samgarcia.onepieceapp.presentation.components.RatingWidget
import edu.samgarcia.onepieceapp.ui.theme.*
import edu.samgarcia.onepieceapp.utils.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    characters: LazyPagingItems<OPCharacter>,
    navHostController: NavHostController
) {
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
        Surface(shape = RoundedCornerShape(size = L_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.character_image),
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                color = Color.Black.copy(alpha = ContentAlpha.medium),
                shape = RoundedCornerShape(
                    bottomStart = L_PADDING,
                    bottomEnd = L_PADDING
                )
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = M_PADDING)
                ) {
                    Text(
                        text = character.name,
                        color = MaterialTheme.colors.homeTopBarTextColor,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = character.about,
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier.padding(top = S_PADDING),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingWidget(
                            modifier = Modifier.padding(end = S_PADDING),
                            rating = character.rating
                        )

                        Text(
                            text = "(${character.rating})",
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