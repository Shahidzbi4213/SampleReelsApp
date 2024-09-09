package com.gulehri.samplereelsapp

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.shahid.iqbal.reelsplayer.actions.VideoSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainVm : ViewModel() {

    var showScreen = MutableStateFlow(0)

    var indexOfVideo = MutableStateFlow(0)

    var currentPage = MutableStateFlow<Int>(indexOfVideo.value)


    val videoList = listOf(
        VideoSource.UrlResource("https://vue-3-tiktok.vercel.app/video1.mp4"),
        VideoSource.UrlResource("https://vue-3-tiktok.vercel.app/video2.mp4"),
        VideoSource.UrlResource("https://i.imgur.com/rzhgpNQ.mp4"),
        VideoSource.UrlResource("https://user-images.githubusercontent.com/90382113/170887700-e405c71e-fe31-458d-8572-aea2e801eecc.mp4"),
        VideoSource.UrlResource("https://user-images.githubusercontent.com/90382113/170890384-43214cc8-79c6-4815-bcb7-e22f6f7fe1bc.mp4"),
        VideoSource.UrlResource("https://user-images.githubusercontent.com/90382113/170889265-7ed9a56c-dd5f-4d78-b453-18b011644da0.mp4"),
        VideoSource.UrlResource("https://user-images.githubusercontent.com/90382113/170885742-d82e3b59-e45a-4fcf-a851-fed58ff5a690.mp4")
    )

}