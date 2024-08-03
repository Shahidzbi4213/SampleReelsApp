package com.shahid.iqbal.reelsplayer.configs

import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.util.RepeatModeUtil
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.shahid.iqbal.reelsplayer.actions.PlayerResizeMode
import com.shahid.iqbal.reelsplayer.actions.RepeatMode
import com.shahid.iqbal.reelsplayer.actions.ThumbnailDisplayMode
import com.shahid.iqbal.reelsplayer.actions.VideoScalingMode

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

@OptIn(UnstableApi::class)
object ReelsConfigUtils {

    private fun getResizeMode(playerResizeMode: PlayerResizeMode): Int {
        return when (playerResizeMode) {
            PlayerResizeMode.FIT -> AspectRatioFrameLayout.RESIZE_MODE_FIT
            PlayerResizeMode.FILL -> AspectRatioFrameLayout.RESIZE_MODE_FILL
            PlayerResizeMode.ZOOM -> AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            PlayerResizeMode.FIXED_WIDTH -> AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
            PlayerResizeMode.FIXED_HEIGHT -> AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
        }
    }

    fun getVideoScalingMode(videoScalingMode: VideoScalingMode): Int {
        return when (videoScalingMode) {
            VideoScalingMode.DEFAULT -> C.VIDEO_SCALING_MODE_DEFAULT
            VideoScalingMode.FIT -> C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            VideoScalingMode.FIT_WITH_CROPPING -> C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        }
    }

    fun getVideoRepeatMode(repeatMode: RepeatMode): Int {
        return when (repeatMode) {
            RepeatMode.CURRENT -> RepeatModeUtil.REPEAT_TOGGLE_MODE_ONE
            RepeatMode.ALL -> RepeatModeUtil.REPEAT_TOGGLE_MODE_ALL
        }
    }

    private fun getThumbnailDisplayMode(thumbnailDisplayMode: ThumbnailDisplayMode): Int {
        return when (thumbnailDisplayMode) {
            ThumbnailDisplayMode.OFF -> PlayerView.ARTWORK_DISPLAY_MODE_OFF
            ThumbnailDisplayMode.FIT -> PlayerView.ARTWORK_DISPLAY_MODE_FIT
            ThumbnailDisplayMode.FILL -> PlayerView.ARTWORK_DISPLAY_MODE_FILL
        }
    }

    fun PlayerView.hideControllersViews() {
        setShowBuffering(PlayerView.SHOW_BUFFERING_NEVER)
        setShowRewindButton(false)
        setShowVrButton(false)
        setShowSubtitleButton(false)
        setShowShuffleButton(false)
        setShowFastForwardButton(false)
    }

    fun PlayerView.setPlayerAttributes(reelConfig: ReelsConfig) {
        layoutParams =
            ViewGroup.LayoutParams(reelConfig.playerSize.width, reelConfig.playerSize.height)
        useController = reelConfig.showControlsMenu
        resizeMode = getResizeMode(reelConfig.playerResizeMode)
        artworkDisplayMode = getThumbnailDisplayMode(reelConfig.thumbnailDisplayMode)
    }

}