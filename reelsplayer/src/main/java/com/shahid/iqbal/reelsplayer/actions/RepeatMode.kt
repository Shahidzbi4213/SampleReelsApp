package com.shahid.iqbal.reelsplayer.actions

import com.shahid.iqbal.reelsplayer.actions.RepeatMode.ALL
import com.shahid.iqbal.reelsplayer.actions.RepeatMode.CURRENT

/**
 * Class defining the repeat modes for video.
 *
 * @property CURRENT Repeats the current video .
 * @property ALL Plays all videos in the list sequentially and repeats from the beginning when the end is reached.
 */

enum class RepeatMode {
    CURRENT,
    ALL
}
