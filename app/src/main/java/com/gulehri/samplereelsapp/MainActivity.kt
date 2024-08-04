package com.gulehri.samplereelsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gulehri.samplereelsapp.ui.theme.SampleReelsAppTheme
import com.shahid.iqbal.reelsplayer.components.ReelsPlayer

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleReelsAppTheme {

                val indexOfVideo = 0
                var currentPage by remember {
                    mutableIntStateOf(indexOfVideo)
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        ReelsPlayer(
                            modifier = Modifier
                                .fillMaxSize(),
                            videoList = listOf(
                                "https://vue-3-tiktok.vercel.app/video1.mp4",
                                "https://vue-3-tiktok.vercel.app/video2.mp4",
                                "https://i.imgur.com/rzhgpNQ.mp4",
                                "https://user-images.githubusercontent.com/90382113/170887700-e405c71e-fe31-458d-8572-aea2e801eecc.mp4",
                                "https://user-images.githubusercontent.com/90382113/170890384-43214cc8-79c6-4815-bcb7-e22f6f7fe1bc.mp4",
                                "https://user-images.githubusercontent.com/90382113/170889265-7ed9a56c-dd5f-4d78-b453-18b011644da0.mp4",
                                "https://user-images.githubusercontent.com/90382113/170885742-d82e3b59-e45a-4fcf-a851-fed58ff5a690.mp4"

                            ),
                            indexOfVideo = indexOfVideo,
                        ) { page ->

                            currentPage = page
                        }


                    }
                }


            }
        }
    }


    @androidx.compose.runtime.Composable
    fun ReelsDetail(page: Int) {

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = page.toString(),
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "User $page")
            }
        }
    }
}
