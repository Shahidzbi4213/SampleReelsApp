package com.shahid.iqbal.reelsplayer.configs

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Environment
import android.os.StatFs
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.util.RepeatModeUtil
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.shahid.iqbal.reelsplayer.actions.PlayerResizeMode
import com.shahid.iqbal.reelsplayer.actions.RepeatMode
import com.shahid.iqbal.reelsplayer.actions.ThumbnailDisplayMode
import com.shahid.iqbal.reelsplayer.actions.VideoScalingMode
import com.shahid.iqbal.reelsplayer.actions.VideoSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

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

    private fun PlayerView.hideControllersViews() {
        setShowBuffering(PlayerView.SHOW_BUFFERING_NEVER)
        setShowRewindButton(false)
        setShowVrButton(false)
        setShowSubtitleButton(false)
        setShowShuffleButton(false)
        setShowFastForwardButton(false)
        setShowNextButton(false)
        setShowPreviousButton(false)
        findViewById<ImageButton>(androidx.media3.ui.R.id.exo_settings).visibility = View.GONE
        findViewById<LinearLayout>(androidx.media3.ui.R.id.exo_time).visibility = View.GONE
    }

    fun PlayerView.setPlayerAttributes(reelConfig: ReelsConfig) {
        layoutParams =
            ViewGroup.LayoutParams(reelConfig.playerSize.width, reelConfig.playerSize.height)
        resizeMode = getResizeMode(reelConfig.playerResizeMode)
        artworkDisplayMode = getThumbnailDisplayMode(reelConfig.thumbnailDisplayMode)
        setControllerAnimationEnabled(true)

        hideControllersViews()
    }


    /*
    * This function will get the video thumbnail and return as a bitmap
    * if its successful in retrieving it.
    */

    suspend fun getVideoThumbnail(context: Context, source: VideoSource): Bitmap? =
        withContext(Dispatchers.IO) {
            try {
                val retriever = MediaMetadataRetriever()

                when (source) {
                    is VideoSource.UrlResource -> {
                        retriever.setDataSource(source.videoUrl, HashMap()) // Network
                    }

                    is VideoSource.RawResource -> {
                        val uri =
                            Uri.parse("android.resource://${context.packageName}/${source.resourceId}")
                        retriever.setDataSource(context, uri)
                    }

                    is VideoSource.AssetResource -> {
                        val afd = context.assets.openFd(source.assetPath)
                        retriever.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                        afd.close()
                    }

                    is VideoSource.HlsResource -> {
                        // Might fail — HLS (m3u8) is not always supported
                        retriever.setDataSource(source.streamingUrl, HashMap())
                    }
                }

                val frame = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)
                retriever.release()
                frame
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

}