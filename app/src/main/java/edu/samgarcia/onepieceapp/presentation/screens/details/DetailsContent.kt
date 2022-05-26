package edu.samgarcia.onepieceapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.domain.model.OPCharacter
import edu.samgarcia.onepieceapp.presentation.components.BulletList
import edu.samgarcia.onepieceapp.presentation.components.InfoBox
import edu.samgarcia.onepieceapp.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedCharacter: OPCharacter?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Expanded
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedCharacter?.let { BottomSheetContent(selectedCharacter = it) }
        },
        content = {}
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
                title = "Devil Fruits",
                items = selectedCharacter.devilFruits,
                textColor = contentColor
            )

            BulletList(
                title = "Family",
                items = selectedCharacter.family,
                textColor = contentColor
            )
        }
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