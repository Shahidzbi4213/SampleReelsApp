package com.shahid.iqbal.reelsplayer.configs

import android.util.Size
import android.view.ViewGroup.LayoutParams
import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import com.shahid.iqbal.reelsplayer.actions.PlayerResizeMode
import com.shahid.iqbal.reelsplayer.actions.RepeatMode
import com.shahid.iqbal.reelsplayer.actions.ThumbnailDisplayMode
import com.shahid.iqbal.reelsplayer.actions.VideoScalingMode
import com.shahid.iqbal.reelsplayer.components.DefaultVideoLoader


/**
 * Configuration class for the Reels feature.
 *
 * @property playerResizeMode The resize mode for the video player.
 * @property videoScalingMode The scaling mode for video display.
 * @property repeatMode The repeat mode for video playback.
 * @property thumbnailDisplayMode The display mode for thumbnails.
 * @property showControlsMenu Whether to show the controls menu.
 * @property playerSize The size of the video player.
 * @property reelDetail A composable function that takes an integer representing the video index
 *                      and displays details for the specified reel.
 * @property playerLoader A composable function to display the video loader.
 */
@Keep
data class ReelsConfig(
    var playerResizeMode: PlayerResizeMode = PlayerResizeMode.FILL,
    var videoScalingMode: VideoScalingMode = VideoScalingMode.FIT_WITH_CROPPING,
    var repeatMode: RepeatMode = RepeatMode.CURRENT,
    var thumbnailDisplayMode: ThumbnailDisplayMode = ThumbnailDisplayMode.FILL,
    val showControlsMenu: Boolean = false,
    var playerSize: Size = Size(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT),
    var reelDetail: (@Composable (Int) -> Unit)? = null,
    var playerLoader: (@Composable () -> Unit)? = { DefaultVideoLoader() },
)

