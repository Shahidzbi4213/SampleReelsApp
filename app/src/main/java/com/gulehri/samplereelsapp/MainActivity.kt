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
                            "https://vue-3-tiktok.vercel.app/video2.mp4"
                        ),
                        indexOfVideo = 0,

                        )
                }
            }
        }
    }
}
