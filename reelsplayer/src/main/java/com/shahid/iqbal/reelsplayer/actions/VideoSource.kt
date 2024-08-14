package com.shahid.iqbal.reelsplayer.actions

/**
 * A sealed class representing different types of video sources.
 */
sealed class VideoSource {

    /**
     * Represents a video source from raw resources.
     *
     * @param resourceId The resource ID of the raw video file.
     */
    data class RawResource(val resourceId: Int) : VideoSource()

    /**
     * Represents a video source from asset resources.
     *
     * Note: When providing the asset path, only specify the file name (e.g., "video.mp4").
     * The "assets:///" prefix is automatically handled by the system.
     *
     * @param assetPath The file name of the video in the assets directory.
     */
    data class AssetResource(val assetPath: String) : VideoSource()

    /**
     * Represents a video source from a URL.
     *
     * @param videoUrl The URL of the video file.
     */
    data class UrlResource(val videoUrl: String) : VideoSource()

    /**
     * Represents a video source from an HLS (HTTP Live Streaming) URL.
     *
     * @param streamingUrl The URL of the HLS streaming source.
     */
    data class HlsResource(val streamingUrl: String) : VideoSource()
}

