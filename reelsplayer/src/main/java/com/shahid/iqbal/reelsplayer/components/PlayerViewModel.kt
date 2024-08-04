package com.shahid.iqbal.reelsplayer.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
 * Created by Shahid Iqbal on 7/20/2024.
 */

class PlayerViewModel : ViewModel() {

    private val _playerUiState = MutableStateFlow(PlayerUiState())
    val playerUiState get() = _playerUiState.asStateFlow()



    fun updatePauseState(pause: Boolean) {
        viewModelScope.launch { _playerUiState.update { it.copy(isPaused = pause) } }
    }

    fun updateLoadingState(loading: Boolean) {
        viewModelScope.launch { _playerUiState.update { it.copy(isLoading = loading) } }
    }


}