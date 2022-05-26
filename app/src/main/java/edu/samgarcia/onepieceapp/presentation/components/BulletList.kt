package edu.samgarcia.onepieceapp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import edu.samgarcia.onepieceapp.ui.theme.XS_PADDING
import edu.samgarcia.onepieceapp.ui.theme.titleColor

@Composable
fun BulletList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.padding(bottom = XS_PADDING))

        Log.d("BulletList", items.size.toString())

        items.forEach { item ->
            if (item.isNotBlank()) {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "• $item",
                    color = textColor,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BulletListPreview() {
    BulletList(
        title = "Places",
        items = listOf("Spain", "Italy", "France"),
        textColor = MaterialTheme.colors.titleColor
    )
}