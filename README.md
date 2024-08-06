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
    videoList = listOf("URLS"),
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




        
## 👨 Developed By

<a href="https://www.linkedin.com/in/shahidzbi/" target="_blank">
  <img src="https://avatars.githubusercontent.com/u/45350491?s=400&u=fbed8c656d79514e0acf50df2aa24a4953a5fd46&v=4" width="70" align="left">
</a>

**Shahid Iqbal**

[![Twitter](https://img.shields.io/badge/-twitter-grey?logo=twitter)](https://twitter.com/shahidzbi)
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
