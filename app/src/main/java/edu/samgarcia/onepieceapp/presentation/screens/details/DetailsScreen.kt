package edu.samgarcia.onepieceapp.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import edu.samgarcia.onepieceapp.utils.Constants.BASE_URL
import edu.samgarcia.onepieceapp.utils.PaletteGenerator
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedCharacter by detailsViewModel.selectedCharacter.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navHostController = navHostController,
            selectedCharacter = selectedCharacter,
            colors = colorPalette
        )
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = PaletteGenerator.convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedCharacter?.img}",
                        context = context
                    )

                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            colors = PaletteGenerator.extractColorsFromBitmap(bitmap)
                        )
                    }
                }
            }
        }
    }
}