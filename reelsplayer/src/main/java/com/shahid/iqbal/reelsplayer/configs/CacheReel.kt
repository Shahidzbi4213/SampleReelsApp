package com.shahid.iqbal.reelsplayer.configs

import androidx.annotation.OptIn
import androidx.compose.runtime.Stable
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource

@Stable
@OptIn(UnstableApi::class)
data class CacheReel(
    val cache: Cache,
    val cacheDataSource: CacheDataSource.Factory,
)
