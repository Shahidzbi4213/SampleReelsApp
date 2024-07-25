package com.shahid.iqbal.reelsplayer.actions

/**
 *Class defining the different modes for displaying video thumbnails.
 *
 * @property OFF Thumbnails are not displayed.
 * @property FIT Thumbnails are scaled to fit within the display area, maintaining aspect ratio.
 * @property FILL Thumbnails are scaled to fill the display area, potentially cropping the image.
 */

enum class ThumbnailDisplayMode {
    OFF, FIT, FILL
}