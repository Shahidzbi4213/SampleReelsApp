package com.shahid.iqbal.reelsplayer.components

import androidx.annotation.OptIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig
import com.shahid.iqbal.reelsplayer.configs.ReelsConfigUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val playerViewModel: PlayerViewModel = viewModel()
    val playerUiState by playerViewModel.playerUiState.collectAsStateWithLifecycle()

    val listOfVideos = remember { videoList }
    val index by remember { mutableIntStateOf(indexOfVideo) }
    val pageState = rememberPagerState(initialPage = index) { listOfVideos.size }
    var scope = rememberCoroutineScope { Dispatchers.IO }

    val cacheReel = remember { ReelsConfigUtils.cachingWorkFactory(context) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            videoScalingMode = ReelsConfigUtils.getVideoScalingMode(reelConfig.videoScalingMode)
            repeatMode = ReelsConfigUtils.getVideoRepeatMode(reelConfig.repeatMode)
            setHandleAudioBecomingNoisy(true)
            setPriority(C.PRIORITY_PLAYBACK)
            increaseDeviceVolume(C.VOLUME_FLAG_PLAY_SOUND)
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
    }

    val lifecycleEventObserver = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> exoPlayer.play()
                Lifecycle.Event.ON_PAUSE -> exoPlayer.pause()
                Lifecycle.Event.ON_DESTROY -> {
                    exoPlayer.stop()
                    exoPlayer.release()
                }

                else -> Unit
            }
        }
    }



    LaunchedEffect(
        key1 = playerUiState.isPaused, key2 = !playerUiState.isPaused
    ) {
        if (!playerUiState.isPaused) exoPlayer.pause()
        else exoPlayer.play()
    }

    LaunchedEffect(pageState.currentPage) {
        with(exoPlayer) {
            setMediaSource(
                ProgressiveMediaSource.Factory(cacheReel.cacheDataSource).createMediaSource(
                        MediaItem.Builder().setUri(videoList[pageState.currentPage]).build()
                    )
            )
            prepare()
        }
    }



    DisposableEffect(key1 = Unit) {
        onDispose {
            scope.launch {
                cacheReel.cache.release()
                exoPlayer.release()
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }


    VerticalPager(state = pageState,
        modifier = modifier,
        beyondBoundsPageCount = 0,
        pageSpacing = pageSpacing,
        contentPadding = contentPadding,
        key = { videoList[it] }) { page ->

        PageContent(
            exoPlayer = exoPlayer,
            page = page,
            pagerState = pageState,
            reelConfig = reelConfig,
            isPlayerLoading = playerUiState.isLoading
        )
    }
}


