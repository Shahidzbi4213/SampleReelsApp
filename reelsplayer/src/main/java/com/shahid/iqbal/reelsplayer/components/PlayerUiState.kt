package com.shahid.iqbal.reelsplayer.components

import androidx.annotation.Keep

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

@Keep
data class PlayerUiState(
    var isLoading: Boolean = false,
    var isPaused: Boolean = false,
)