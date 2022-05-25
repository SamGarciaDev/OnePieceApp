package edu.samgarcia.onepieceapp.presentation.components

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.ui.theme.INFO_ICON_SIZE
import edu.samgarcia.onepieceapp.ui.theme.XS_PADDING
import edu.samgarcia.onepieceapp.ui.theme.titleColor

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(INFO_ICON_SIZE),
            painter = icon,
            tint = iconColor,
            contentDescription = stringResource(R.string.info_icon)
        )
        
        Spacer(modifier = Modifier.padding(end = XS_PADDING))

        Column {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize
            )

            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun InfoBoxPreview() {
    InfoBox(
        icon = painterResource(id = R.drawable.ic_berry),
        iconColor = MaterialTheme.colors.primary,
        bigText = "1,200,000",
        smallText = "Bounty",
        textColor = MaterialTheme.colors.titleColor
    )
}