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
 * Configuration class for customizing the behavior and appearance of the Reels feature.
 *
 * @property playerResizeMode The resize mode for the video player, which determines how the video content
 *                            will be scaled or cropped to fit within the player's bounds.
 * @property videoScalingMode The scaling mode for video display, which specifies how video frames should
 *                            be scaled before rendering.
 * @property repeatMode The repeat mode for video playback, defining how the video should behave when it reaches
 *                      the end (e.g., loop the current video, move to the next, or stop).
 * @property thumbnailDisplayMode The display mode for thumbnails, which controls how thumbnails are scaled
 *                                and displayed within the UI.
 * @property enableCache A flag indicating whether to cache the video content. When set to true, the player
 *                       will cache videos locally to allow for smoother playback as the user scrolls
 *                       through the list of videos.
 * @property playerSize The size of the video player, typically defined in terms of width and height.
 * @property playerLoader An optional composable function to display a custom loader while the video is buffering
 *                        or loading. If null, no loader will be displayed.
 */
@Stable
@Keep
data class ReelsConfig(
    val playerResizeMode: PlayerResizeMode = PlayerResizeMode.FILL,
    val videoScalingMode: VideoScalingMode = VideoScalingMode.DEFAULT,
    val repeatMode: RepeatMode = RepeatMode.CURRENT,
    val thumbnailDisplayMode: ThumbnailDisplayMode = ThumbnailDisplayMode.FILL,
    val enableCache: Boolean = true,
    val playerSize: Size = Size(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT),
    val playerLoader: (@Composable () -> Unit)? = null,
)

