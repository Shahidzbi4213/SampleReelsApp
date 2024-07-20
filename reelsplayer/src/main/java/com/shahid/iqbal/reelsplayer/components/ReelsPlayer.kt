package com.shahid.iqbal.reelsplayer.components

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

@OptIn(UnstableApi::class)
@Composable
fun ReelsPlayer(
    modifier: Modifier = Modifier,
    reelConfig: ReelsConfig = ReelsConfig(),
    videoUrl: String,
) {

    val playerViewModel: PlayerViewModel = viewModel()
    val playerUiState by playerViewModel.playerUiState.collectAsStateWithLifecycle()
    val exoPlayer = rememberExoplayer(reelConfig = reelConfig).apply {
        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {

                    Player.STATE_BUFFERING -> {
                        playerViewModel.updateLoadingState(true)
                    }

                    Player.STATE_READY -> {
                        playerViewModel.updateLoadingState(true)
                    }

                    else -> Unit
                }
            }
        })
    }

    LaunchedEffect(key1 = playerUiState.isPaused, key2 = !playerUiState.isPaused) {
        if (!playerUiState.isPaused) exoPlayer.pause()
        else exoPlayer.play()

    }

}
