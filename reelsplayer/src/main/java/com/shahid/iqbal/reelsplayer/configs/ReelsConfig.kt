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


@Keep
data class ReelsConfig(
    var playerResizeMode: PlayerResizeMode = PlayerResizeMode.FILL,
    var videoScalingMode: VideoScalingMode = VideoScalingMode.FIT_WITH_CROPPING,
    var repeatMode: RepeatMode = RepeatMode.CURRENT,
    var thumbnailDisplayMode: ThumbnailDisplayMode = ThumbnailDisplayMode.FILL,
    val showControlsMenu: Boolean = false,
    var playerSize: Size = Size(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT),
    var reelDetail: (@Composable () -> Unit)? = null,
    var playerLoader: (@Composable () -> Unit)? = { DefaultVideoLoader() },
)
