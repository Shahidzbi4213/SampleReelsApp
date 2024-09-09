package com.gulehri.samplereelsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import com.gulehri.samplereelsapp.ui.theme.SampleReelsAppTheme
import com.shahid.iqbal.reelsplayer.actions.VideoSource
import com.shahid.iqbal.reelsplayer.components.ReelsPlayer
import com.shahid.iqbal.reelsplayer.configs.ReelsConfig

/*
* */

class MainActivity : ComponentActivity() {

    private val mainVm by viewModels<MainVm>()

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleReelsAppTheme {


                val showScreen by mainVm.showScreen.collectAsStateWithLifecycle()
                val indexOfVideo by mainVm.indexOfVideo.collectAsStateWithLifecycle()

                when (showScreen) {
                    0 -> {
                        ReelScreen()
                    }

                    1 -> {
                        ReelsDetail(indexOfVideo = indexOfVideo)
                    }
                }

            }
        }
    }

    @Composable
    fun ReelScreen() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(100.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {


                    items(mainVm.videoList) {
                        SingleTrendingItem(url = it.videoUrl) {
                            mainVm.showScreen.value = 1
                            mainVm.indexOfVideo.value = mainVm.videoList.indexOf(it)
                        }
                    }


                }
            }

        }
    }

    @Composable
    fun SingleTrendingItem(url: String, onClick: () -> Unit) {

        val context = LocalContext.current
        val model = remember { ImageRequest.Builder(context).data(url).crossfade(true).build() }
        val imageLoader = remember { ImageLoader.Builder(context).components { add(VideoFrameDecoder.Factory()) }.build() }


        AsyncImage(
            model = model,
            contentDescription = null,
            imageLoader = imageLoader,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ReelsDetail(indexOfVideo: Int) {

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


            BackHandler {
                mainVm.showScreen.value = 0
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                ReelsPlayer(
                    modifier = Modifier.fillMaxSize(),
                    reelConfig = ReelsConfig(),
                    videoList = mainVm.videoList,
                    indexOfVideo = indexOfVideo,
                ) { page ->

                    mainVm.currentPage.value = page
                }


            }
        }

    }
}
