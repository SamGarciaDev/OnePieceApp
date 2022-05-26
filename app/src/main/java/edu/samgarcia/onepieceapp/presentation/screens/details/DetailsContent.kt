package edu.samgarcia.onepieceapp.presentation.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.presentation.components.BulletList
import edu.samgarcia.onepieceapp.presentation.components.InfoBox
import edu.samgarcia.onepieceapp.ui.theme.*
import edu.samgarcia.onepieceapp.utils.Constants.BASE_URL
import edu.samgarcia.onepieceapp.utils.PaletteGenerator

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedCharacter: OPCharacter?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#FFFFFF") }

    LaunchedEffect(key1 = selectedCharacter) {
        vibrant = colors[PaletteGenerator.VIBRANT]!!
        darkVibrant = colors[PaletteGenerator.DARK_VIBRANT]!!
        onDarkVibrant = colors[PaletteGenerator.ON_DARK_VIBRANT]!!
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Expanded
        )
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction
    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) S_PADDING else 0.dp
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_COLLAPSED_HEIGHT,
        sheetContent = {
            selectedCharacter?.let {
                BottomSheetContent(
                    selectedCharacter = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedCharacter?.let {
                BackgroundContent(
                    characterImage = it.img,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedCharacter: OPCharacter,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = S_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = M_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedCharacter.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = S_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_berry),
                iconColor = infoBoxIconColor,
                bigText = "%,d".format(selectedCharacter.bounty),
                smallText = stringResource(R.string.bounty),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_world),
                iconColor = infoBoxIconColor,
                bigText = selectedCharacter.origin,
                smallText = stringResource(R.string.origin),
                textColor = contentColor
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = selectedCharacter.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 7
        )

        Spacer(modifier = Modifier.padding(bottom = S_PADDING))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BulletList(
                modifier = Modifier.weight(1f),
                title = "Devil Fruits",
                items = selectedCharacter.devilFruits,
                textColor = contentColor
            )

            Spacer(modifier = Modifier.padding(XS_PADDING))

            BulletList(
                modifier = Modifier.weight(1f),
                title = "Family",
                items = selectedCharacter.family,
                textColor = contentColor
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    characterImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${characterImage}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.ic_placeholder)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.character_image),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = XS_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Preview
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedCharacter = OPCharacter(
            id = 1,
            name = "Luffy",
            img = "",
            about = "Some random text...",
            origin = "West Blue",
            bounty = 550000000,
            rating = 3.6,
            devilFruits = listOf("Gomu Gomu no mi"),
            family = listOf("Portgas D. Ace")
        ))
}