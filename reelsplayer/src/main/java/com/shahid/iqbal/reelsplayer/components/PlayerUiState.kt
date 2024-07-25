package com.shahid.iqbal.reelsplayer.components

import androidx.annotation.Keep

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

/**
 * A data class representing the UI state of the player.
 *
 * @property isLoading A boolean indicating whether the player is currently loading content.
 * @property isPaused A boolean indicating whether the player is paused.
 */
@Keep
data class PlayerUiState(
    var isLoading: Boolean = false,
    var isPaused: Boolean = false,
)
