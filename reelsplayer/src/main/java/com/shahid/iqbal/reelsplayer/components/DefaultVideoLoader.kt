package com.shahid.iqbal.reelsplayer.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

/**
 * A composable function that displays a default loading indicator for video content.
 * This is typically used while the video is buffering or loading.
 *
 * @param modifier A [Modifier] to apply to this layout.
 * @param progressColor The color of the progress indicator.
 * @param strokeWidth The width of the stroke for the progress indicator.
 */

@Preview(showBackground = true)
@Composable
fun DefaultVideoLoader(
    modifier: Modifier = Modifier,
    progressColor: Color = Color.White,
    strokeWidth: Dp = 5.dp,
) {
    CircularProgressIndicator(
        modifier = modifier.size(50.dp),
        color = progressColor,
        strokeWidth = strokeWidth,
        strokeCap = StrokeCap.Round
    )
}
