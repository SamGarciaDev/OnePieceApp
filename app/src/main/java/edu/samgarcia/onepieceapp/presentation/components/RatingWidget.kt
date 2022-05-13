package edu.samgarcia.onepieceapp.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.samgarcia.onepieceapp.R
import edu.samgarcia.onepieceapp.ui.theme.StarColor
import edu.samgarcia.onepieceapp.ui.theme.XXS_PADDING


@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 3f,
    spaceBetween: Dp = XXS_PADDING
) {
    val starPathString = stringResource(id = R.string.star_path)

    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }

    val starPathBounds = remember {
        starPath.getBounds()
    }

    val result = calculateStars(rating = rating)

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spaceBetween)) {
        result["filledStars"]?.let {
            repeat(it) {
                FilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }

        result["halfFilledStars"]?.let {
            repeat(it) {
                HalfFilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }

        result["emptyStars"]?.let {
            repeat(it) {
                EmptyStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
    }
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top) {
                drawPath(path = starPath, color = StarColor)
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top) {
                drawPath(path = starPath, color = Color.LightGray.copy(alpha = 0.5f))

                clipPath(path = starPath) {
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBounds.maxDimension / 1.7f,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left, top) {
                drawPath(path = starPath, color = Color.LightGray.copy(0.5f))
            }
        }
    }
}

@Composable
fun calculateStars(rating: Double): Map<String, Int> {
    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (unit, decimal) = rating
            .toString()
            .split(".")
            .map { it.toInt() }

        if (unit in 0..5 && decimal in 0..9) {
            filledStars = unit

            if (decimal in 1..5) {
                halfFilledStars++
            } else if (decimal in 6..9) {
                filledStars++
            }

            if (unit == 5 && decimal > 0) {
                emptyStars = 5
                halfFilledStars = 0
                filledStars = 0
            }
        } else {
            Log.d("RatingWidget", "Invalid rating number.")
        }

    }

    emptyStars = maxStars - (filledStars + halfFilledStars)

    return mapOf(
        "filledStars" to filledStars,
        "halfFilledStars" to halfFilledStars,
        "emptyStars" to emptyStars
    )
}

@Composable
@Preview(showBackground = true)
fun FilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)

    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }

    val starPathBounds = remember {
        starPath.getBounds()
    }

    FilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}

@Composable
@Preview(showBackground = true)
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)

    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }

    val starPathBounds = remember {
        starPath.getBounds()
    }

    HalfFilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}

@Composable
@Preview(showBackground = true)
fun EmptyStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)

    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }

    val starPathBounds = remember {
        starPath.getBounds()
    }

    EmptyStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}

