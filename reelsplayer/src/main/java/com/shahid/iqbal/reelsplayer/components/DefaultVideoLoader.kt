package com.shahid.iqbal.reelsplayer.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
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
@Composable
fun DefaultVideoLoader(
    modifier: Modifier = Modifier,
    progressColor: Color = Color.White,
    strokeWidth: Dp = 5.dp,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier.align(Alignment.Center),
            color = progressColor,
            strokeWidth = strokeWidth,
            strokeCap = StrokeCap.Round
        )
    }
}
