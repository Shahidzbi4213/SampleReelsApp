package com.shahid.iqbal.reelsplayer.components

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.AssetDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.shahid.iqbal.reelsplayer.actions.VideoSource
import com.shahid.iqbal.reelsplayer.configs.CacheReel

/*
 * Created by Shahid Iqbal on 8/14/2024.
 */


/**
 * Creates a MediaSource based on the provided VideoSource. This function supports multiple video sources,
 * including assets, raw resources, URLs, and HLS streams. It also allows for optional caching of media data.
 *
 * @param context The context used to access resources such as assets and raw resources.
 * @param videoSource The source of the video, represented by the VideoSource sealed class.
 * @param isCacheEnable A flag to indicate whether caching is enabled. If true, the video will be cached
 *                      locally for smoother playback.
 * @param cacheReel The CacheReel configuration used to manage caching, required if caching is enabled.
 *
 * @return A @MediaSource instance configured according to the provided video source.
 */

@OptIn(UnstableApi::class)
fun getMediaSource(
    context: Context,
    videoSource: VideoSource,
    isCacheEnable: Boolean,
    cacheReel: CacheReel,
): MediaSource {

    return when (videoSource) {
        is VideoSource.AssetResource -> {
            ProgressiveMediaSource.Factory { AssetDataSource(context) }.createMediaSource(
                MediaItem.Builder().setUri(
                    if (videoSource.assetPath.startsWith("assets:///")) {
                        videoSource.assetPath
                    } else {
                        "assets:///".plus(videoSource.assetPath)
                    }

                ).build()
            )
        }

        is VideoSource.RawResource -> {
            ProgressiveMediaSource.Factory { RawResourceDataSource(context) }.createMediaSource(
                MediaItem.Builder().setUri(
                    Uri.Builder().scheme(
                        ContentResolver.SCHEME_ANDROID_RESOURCE
                    ).path(videoSource.resourceId.toString()).build()
                ).build()
            )
        }

        is VideoSource.UrlResource -> {
            ProgressiveMediaSource.Factory(
                if (isCacheEnable) cacheReel.cacheDataSource
                else DefaultHttpDataSource.Factory()
            ).createMediaSource(
                MediaItem.Builder().setUri(videoSource.videoUrl).build()
            )
        }

        is VideoSource.HlsResource -> {
            HlsMediaSource.Factory(
                if (isCacheEnable) cacheReel.cacheDataSource else DefaultHttpDataSource.Factory()
            ).createMediaSource(
                MediaItem.Builder().setUri(videoSource.streamingUrl).build()
            )
        }
    }
}

