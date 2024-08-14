<h1 align="center">Reels Player</h1>
<h4 align="center">A Jetpack Compose library to Play Videos Like Tiktok/Instagram .</h4>


<div align="center">
  <h4>Built with ❤︎ by
  <a href="https://twitter.com/shahidzbi">Shahid Iqbal</a>
  </h4>
</div>
<br/>
<br/>

## Demo
https://github.com/user-attachments/assets/8ffe9b12-66d2-45db-8fbc-206bd19d8ba0

<br/>
<br/>
        
## 💻 Installation
In `settings.gradle` of  `app` module include this 


```
 maven(url = "https://jitpack.io")
````

In `build.gradle` of `app` module, include this dependency
        
```
 implementation ("com.github.Shahidzbi4213:SampleReelsApp:x.y.z")
```
        
Please replace x, y and z with the latest version numbers ![](https://img.shields.io/jitpack/version/com.github.Shahidzbi4213/SampleReelsApp.svg).
        
Or you can find latest version and changelogs in the [releases](https://github.com/Shahidzbi4213/SampleReelsApp/releases).

<br/>
        
## ❓ Usage

```kotlin
  ReelsPlayer(
    modifier = Modifier.fillMaxSize(),
    videoList = listOf(VideoSource),
    indexOfVideo = 0,
    config = ReelsConfig(
        playerResizeMode = PlayerResizeMode.FILL,
        videoScalingMode = VideoScalingMode.FIT_WITH_CROPPING,
        repeatMode = RepeatMode.ALL,
        thumbnailDisplayMode = ThumbnailDisplayMode.FILL,
        enableCache = true,
        playerSize = Size(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    )
) { page ->
    // Perform Other Operation Based on Page Number
}


```


## 📹 Video Source Types

The `ReelsPlayer` library supports various video source types. Below is a table summarizing the supported video sources and their usage:

| **Source Type** | **Description**                                             | **Usage Example**                                      |
|-----------------|-------------------------------------------------------------|--------------------------------------------------------|
| **Raw Resources** | Videos stored in your app’s `res/raw` directory.          | `videoList = listOf(VideoSource.RawResource(R.raw.video_file))` |
| **Assets**       | Videos included in your app’s `assets` folder.            | `videoList = listOf(VideoSource.AssetResource("file:///android_asset/video_file.mp4"))` |
| **URLs**         | Videos streamed from external URLs.                        | `videoList = listOf(VideoSource.UrlResource("https://example.com/video.mp4"))` |
| **HLS Media**    | Videos using HTTP Live Streaming (HLS) for adaptive bitrate streaming. | `videoList = listOf(VideoSource.HlsResource("http://streaningvideo.com"))` |

Each video source type can be specified using the `VideoSource` enum, which allows for greater flexibility in how videos are integrated and played in your app.

## 🎛 Player Resize Modes

The `PlayerResizeMode` enum class defines how the video should be resized to fit within the player view. Below is a table summarizing each resize mode and its behavior:

| **Resize Mode**    | **Description**                                           | **Usage Example**                                    |
|--------------------|-----------------------------------------------------------|------------------------------------------------------|
| **FIT**            | Scales the video to fit within the player view while maintaining its aspect ratio. The entire video is visible. | `playerResizeMode = PlayerResizeMode.FIT`            |
| **FILL**           | Scales the video to fill the entire player view, potentially cropping parts of the video to maintain aspect ratio. | `playerResizeMode = PlayerResizeMode.FILL`           |
| **ZOOM**           | Scales the video to fill the player view, cropping it if necessary. The video is zoomed in to fill the view completely. | `playerResizeMode = PlayerResizeMode.ZOOM`           |
| **FIXED_WIDTH**    | Resizes the video to a fixed width, maintaining its aspect ratio. The height is adjusted accordingly. | `playerResizeMode = PlayerResizeMode.FIXED_WIDTH`    |
| **FIXED_HEIGHT**   | Resizes the video to a fixed height, maintaining its aspect ratio. The width is adjusted accordingly. | `playerResizeMode = PlayerResizeMode.FIXED_HEIGHT`   |

Select the appropriate resize mode based on how you want the video to fit within the player view while preserving the desired visual effect.

## 🎨 Video Scaling Modes

The `VideoScalingMode` enum class defines how the video should be scaled to fit within the player view. Below is a table summarizing each scaling mode and its behavior:

| **Scaling Mode**        | **Description**                                           | **Usage Example**                                      |
|-------------------------|-----------------------------------------------------------|--------------------------------------------------------|
| **DEFAULT**             | Uses the default scaling behavior. Typically, this mode preserves the video’s aspect ratio without additional scaling adjustments. | `videoScalingMode = VideoScalingMode.DEFAULT`         |
| **FIT**                 | Scales the video to fit within the player view while maintaining its aspect ratio. The entire video is visible. | `videoScalingMode = VideoScalingMode.FIT`             |
| **FIT_WITH_CROPPING**   | Scales the video to fit within the player view while maintaining its aspect ratio. The video may be cropped to fit the view completely. | `videoScalingMode = VideoScalingMode.FIT_WITH_CROPPING` |

Choose the appropriate scaling mode to control how the video is displayed within the player view, considering aspects like visibility and cropping.

## 📸 Thumbnail Display Modes

The `ThumbnailDisplayMode` enum class defines how thumbnails should be displayed in the player view. Below is a table summarizing each display mode and its behavior:

| **Display Mode** | **Description**                                           | **Usage Example**                                    |
|------------------|-----------------------------------------------------------|------------------------------------------------------|
| **OFF**          | Disables thumbnail display. No thumbnails will be shown. | `thumbnailDisplayMode = ThumbnailDisplayMode.OFF`   |
| **FIT**          | Scales the thumbnail to fit within the player view while maintaining its aspect ratio. The entire thumbnail is visible. | `thumbnailDisplayMode = ThumbnailDisplayMode.FIT`   |
| **FILL**         | Scales the thumbnail to fill the entire player view, potentially cropping parts of the thumbnail to maintain aspect ratio. | `thumbnailDisplayMode = ThumbnailDisplayMode.FILL`  |

Select the appropriate thumbnail display mode based on how you want thumbnails to appear in the player view.

## 🔁 Repeat Modes

The `RepeatMode` enum class defines how videos should be repeated during playback. Below is a table summarizing each repeat mode and its behavior:

| **Repeat Mode** | **Description**                                                        | **Usage Example**                                   |
|-----------------|------------------------------------------------------------------------|-----------------------------------------------------|
| **CURRENT**     | Repeats the current video. When the video finishes, it starts over from the beginning. | `repeatMode = RepeatMode.CURRENT`                   |
| **ALL**         | Plays all videos in the list sequentially and repeats from the beginning when the end is reached. | `repeatMode = RepeatMode.ALL`                       |

Choose the appropriate repeat mode to control how the videos are replayed during playback.




        
## 👨 Developed By

<a href="https://www.linkedin.com/in/shahidzbi/" target="_blank">
  <img src="https://avatars.githubusercontent.com/u/45350491?s=400&u=fbed8c656d79514e0acf50df2aa24a4953a5fd46&v=4" width="70" align="left">
</a>

**Shahid Iqbal**

[![Twitter](https://img.shields.io/badge/-twitter-grey?logo=x)](https://twitter.com/shahidzbi)
[![Web](https://img.shields.io/badge/-web-grey?logo=appveyor)](https://shahidzbi.blogspot.com/)
[![Medium](https://img.shields.io/badge/-medium-grey?logo=medium)](https://medium.com/@shahid.iqbal4213)
[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/shahidzbi/)

<br/>

## 👍 How to Contribute
1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

<br/>
        
## 📃 License

    Copyright 2024 Shahid Iqbal

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
