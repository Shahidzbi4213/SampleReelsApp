package com.shahid.iqbal.reelsplayer.actions


/**
 * Class defining the scaling modes for displaying video content.
 *
 * @property DEFAULT Uses the default scaling behavior, which may vary based on the player implementation.
 * @property FIT Scales the video to fit within the player's bounds while maintaining the aspect ratio.
 * @property FIT_WITH_CROPPING Scales the video to fill the player's bounds, which may involve cropping parts of the video.
 */
enum class VideoScalingMode {
    DEFAULT, FIT, FIT_WITH_CROPPING
}
