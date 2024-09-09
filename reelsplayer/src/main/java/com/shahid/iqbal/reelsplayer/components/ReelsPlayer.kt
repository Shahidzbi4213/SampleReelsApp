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
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.shahid.iqbal.reelsplayer.actions.VideoSource
import com.shahid.iqbal.reelsplayer.configs.CacheInstance
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig
import com.shahid.iqbal.reelsplayer.configs.ReelsConfigUtils

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

/**
 * A composable function for playing a list of videos, providing an experience similar to Instagram
 * and TikTok. This function supports various types of video sources, including URLs, raw resources,
 * assets, and HLS streams.
 *
 * @param modifier A [Modifier] to apply to this layout.
 * @param reelConfig The configuration settings for the reels player.
 * @param videoList A list of [VideoSource] objects representing the videos to be played.
 *                  These can include URLs, raw resources, assets, or HLS streams.
 * @param indexOfVideo The initial index of the video to start playing.
 * @param pageSpacing The spacing between pages in the pager.
 * @param contentPadding Padding to be applied to the content inside the pager.
 * @param currentPage A lambda function to be invoked when the current page (video) changes.
 */

@ExperimentalFoundationApi
@OptIn(UnstableApi::class)
@Composable
fun ReelsPlayer(
    modifier: Modifier = Modifier,
    reelConfig: ReelsConfig = ReelsConfig(),
    videoList: List<VideoSource>,
    indexOfVideo: Int = 0,
    pageSpacing: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    currentPage: (Int) -> Unit,

    ) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val playerViewModel: PlayerViewModel = viewModel()
    val playerUiState by playerViewModel.playerUiState.collectAsStateWithLifecycle()


    val listOfVideos = remember { videoList }
    val index by remember { mutableIntStateOf(indexOfVideo) }
    val pageState = rememberPagerState(initialPage = index) { listOfVideos.size }

    val cacheReel = remember { CacheInstance.cachingWorkFactory(context) }

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

                    if (reelConfig.enableCache) CacheInstance.clearResources(context, cacheReel)

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
                getMediaSource(
                    context,
                    videoList[pageState.currentPage],
                    reelConfig.enableCache,
                    cacheReel
                )
            )
            prepare()
        }
        currentPage(pageState.currentPage)
    }



    DisposableEffect(key1 = Unit) {
        onDispose { exoPlayer.release() }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }


    VerticalPager(state = pageState,
        modifier = modifier,
        beyondViewportPageCount = 0,
        pageSpacing = pageSpacing,
        contentPadding = contentPadding,
        key = { videoList[it].toString() }) { page ->

        PageContent(
            exoPlayer = exoPlayer,
            page = page,
            pagerState = pageState,
            reelConfig = reelConfig,
            isPlayerLoading = playerUiState.isLoading
        )
    }
}


