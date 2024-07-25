package com.shahid.iqbal.reelsplayer.components

import androidx.annotation.OptIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

/**
 * A composable function for playing a list of videos similar to the experience on Instagram and TikTok.

 * @param modifier A [Modifier] to apply to this layout.
 * @param reelConfig The configuration settings for the reels player.
 * @param videoList A list of video URLs to be played.
 * @param indexOfVideo The initial index of the video to start playing.
 * @param pageSpacing The spacing between pages in the pager.
 * @param contentPadding Padding to be applied to the content inside the pager.
 */
@ExperimentalFoundationApi
@OptIn(UnstableApi::class)
@Composable
fun ReelsPlayer(
    modifier: Modifier = Modifier,
    reelConfig: ReelsConfig = ReelsConfig(),
    videoList: List<String>,
    indexOfVideo: Int = 0,
    pageSpacing: Dp = 10.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val playerViewModel: PlayerViewModel = viewModel()
    val playerUiState by playerViewModel.playerUiState.collectAsStateWithLifecycle()
    val pageState = rememberPagerState(initialPage = indexOfVideo) { videoList.size }
    val exoPlayer = rememberExoplayer(reelConfig = reelConfig).apply {
        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        playerViewModel.updateLoadingState(true)
                    }

                    Player.STATE_READY -> {
                        playerViewModel.updateLoadingState(false)
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

    VerticalPager(
        state = pageState,
        modifier = modifier.fillMaxSize(),
        beyondBoundsPageCount = 0,
        pageSpacing = pageSpacing,
        contentPadding = contentPadding,
        key = { videoList[it] }
    ) {
        rememberPlayerView(exoPlayer = exoPlayer, reelConfig = reelConfig)
    }
}

