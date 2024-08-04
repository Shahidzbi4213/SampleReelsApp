package com.shahid.iqbal.reelsplayer.configs

import android.util.Size
import android.view.ViewGroup.LayoutParams
import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.shahid.iqbal.reelsplayer.actions.PlayerResizeMode
import com.shahid.iqbal.reelsplayer.actions.RepeatMode
import com.shahid.iqbal.reelsplayer.actions.ThumbnailDisplayMode
import com.shahid.iqbal.reelsplayer.actions.VideoScalingMode


/**
 * Configuration class for the Reels feature.
 *
 * @property playerResizeMode The resize mode for the video player.
 * @property videoScalingMode The scaling mode for video display.
 * @property repeatMode The repeat mode for video playback.
 * @property thumbnailDisplayMode The display mode for thumbnails.
 * @property showControlsMenu Whether to show the controls menu.
 * @property enableCache Whether to cache the video and load from cache if user scroll back and forth.
 * @property playerSize The size of the video player.
 * @property playerLoader A composable function to display the video loader.
 */
@Stable
@Keep
data class ReelsConfig(
    val playerResizeMode: PlayerResizeMode = PlayerResizeMode.FILL,
    val videoScalingMode: VideoScalingMode = VideoScalingMode.FIT_WITH_CROPPING,
    val repeatMode: RepeatMode = RepeatMode.CURRENT,
    val thumbnailDisplayMode: ThumbnailDisplayMode = ThumbnailDisplayMode.FILL,
    val showControlsMenu: Boolean = false,
    val enableCache: Boolean = true,
    val playerSize: Size = Size(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT),
    val playerLoader: (@Composable () -> Unit)? = null,
)

