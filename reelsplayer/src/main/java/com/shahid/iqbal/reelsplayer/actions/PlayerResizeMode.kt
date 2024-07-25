package com.shahid.iqbal.reelsplayer.actions

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

/**
 * Class defining the various modes for resizing video content within the Reels Player.
 *
 * @property FIT Adjusts the video size to fit within the player's bounds while maintaining the aspect ratio.
 * @property FILL Scales the video to fill the entire player bounds, potentially cropping the video if necessary.
 * @property ZOOM Zooms into the video to fill the player's width or height, which may crop some parts of the video.
 * @property FIXED_WIDTH Maintains a fixed width for the video, adjusting the height to maintain aspect ratio.
 * @property FIXED_HEIGHT Maintains a fixed height for the video, adjusting the width to maintain aspect ratio.
 */
enum class PlayerResizeMode {
    FIT, FILL, ZOOM, FIXED_WIDTH, FIXED_HEIGHT
}
