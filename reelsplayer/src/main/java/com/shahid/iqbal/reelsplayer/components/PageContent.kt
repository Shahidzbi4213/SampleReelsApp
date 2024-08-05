package com.shahid.iqbal.reelsplayer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig
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

    var showControlsMenu by remember {
        mutableStateOf(false)
    }



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
                    useController = true
                    controllerAutoShow = false
                    controllerHideOnTouch = false
                    hideController()

                    setOnClickListener {
                        showControlsMenu = !showControlsMenu

                        if (showControlsMenu)
                            showController()
                        else hideController()
                    }
                }
            }, modifier = Modifier
                .fillMaxSize(), update = {
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

@ReadOnlyComposable
@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}