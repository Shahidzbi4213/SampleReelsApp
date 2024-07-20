package com.shahid.iqbal.reelsplayer.components

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig
import com.shahid.iqbal.reelsplayer.configs.ReelsConfigUtils

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

@OptIn(UnstableApi::class)
@Composable
fun rememberExoplayer(reelConfig: ReelsConfig): ExoPlayer {

    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            videoScalingMode = ReelsConfigUtils.getVideoScalingMode(reelConfig.videoScalingMode)
            repeatMode = ReelsConfigUtils.getVideoRepeatMode(reelConfig.repeatMode)
        }
    }

    return exoPlayer
}