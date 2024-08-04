package com.shahid.iqbal.reelsplayer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig
import com.shahid.iqbal.reelsplayer.configs.ReelsConfigUtils.hideControllersViews
import com.shahid.iqbal.reelsplayer.configs.ReelsConfigUtils.setPlayerAttributes

/*
 * Created by Shahid Iqbal on 8/3/2024.
 */

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageContent(
    modifier: Modifier = Modifier,
    exoPlayer: ExoPlayer,
    page: Int,
    pagerState: PagerState,
    reelConfig: ReelsConfig,
    isPlayerLoading: Boolean,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        if (page == pagerState.currentPage) {

            AndroidView({ ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    setPlayerAttributes(reelConfig)
                    hideControllersViews()

                    if (!reelConfig.showControlsMenu) hideController()
                }
            }, modifier = Modifier.fillMaxSize(), update = {
                exoPlayer.playWhenReady = true
            }, onRelease = {
                it.player = null
            })

            /*Show Loader If Player is Buffering*/
            if (isPlayerLoading) {
                reelConfig.playerLoader?.invoke() ?: DefaultVideoLoader(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)

                )
            }
        }
    }


}