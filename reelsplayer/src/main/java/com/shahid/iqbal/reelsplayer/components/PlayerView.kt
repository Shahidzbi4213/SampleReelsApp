package com.shahid.iqbal.reelsplayer.components;

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig
import com.shahid.iqbal.reelsplayer.configs.ReelsConfigUtils.hideControllersViews
import com.shahid.iqbal.reelsplayer.configs.ReelsConfigUtils.setPlayerAttributes

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

@OptIn(UnstableApi::class)
@Composable
fun rememberPlayerView(exoPlayer: ExoPlayer, reelConfig: ReelsConfig): PlayerView {
    val context = LocalContext.current
    val playerView = remember {
        PlayerView(context).apply {
            player = exoPlayer

            setPlayerAttributes(reelConfig)
            hideControllersViews()
        }
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            playerView.player = null
        }
    }
    return playerView
}