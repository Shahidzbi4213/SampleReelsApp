package com.gulehri.samplereelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gulehri.samplereelsapp.ui.theme.SampleReelsAppTheme
import com.shahid.iqbal.reelsplayer.components.ReelsPlayer

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleReelsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    ReelsPlayer(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        videoList = listOf(
                            "https://vue-3-tiktok.vercel.app/video1.mp4",
                            "https://vue-3-tiktok.vercel.app/video2.mp4",
                            "https://i.imgur.com/rzhgpNQ.mp4",
                            "https://user-images.githubusercontent.com/90382113/170887700-e405c71e-fe31-458d-8572-aea2e801eecc.mp4",
                            "https://user-images.githubusercontent.com/90382113/170890384-43214cc8-79c6-4815-bcb7-e22f6f7fe1bc.mp4",
                            "https://user-images.githubusercontent.com/90382113/170889265-7ed9a56c-dd5f-4d78-b453-18b011644da0.mp4",
                            "https://user-images.githubusercontent.com/90382113/170885742-d82e3b59-e45a-4fcf-a851-fed58ff5a690.mp4"

                        ),
                        indexOfVideo = 0,

                        )
                }
            }
        }
    }
}
