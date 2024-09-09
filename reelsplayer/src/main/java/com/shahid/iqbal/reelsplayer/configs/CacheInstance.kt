package com.shahid.iqbal.reelsplayer.configs

import android.content.Context
import android.os.Environment
import android.os.StatFs
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

object CacheInstance {

    @Volatile
    private var cacheReelInstance: CacheReel? = null

    fun cachingWorkFactory(context: Context): CacheReel {
        return cacheReelInstance ?: synchronized(this) {
            cacheReelInstance ?: createCacheReel(context).also { cacheReelInstance = it }
        }
    }

    private fun getDirectory(context: Context): File = File(context.cacheDir, "ReelsPlayer")

    private fun getAvailableSpace(): Long {
        val externalStorageDir = Environment.getExternalStorageDirectory()
        val stat = StatFs(externalStorageDir.path)
        val bytesAvailable = stat.availableBlocksLong * stat.blockSizeLong
        return (bytesAvailable * 0.20).toLong()
    }


    @OptIn(UnstableApi::class)
    private fun createCacheReel(context: Context): CacheReel {
        val databaseProvider = StandaloneDatabaseProvider(context)
        val maxBytes = getAvailableSpace()

        val cache = SimpleCache(
            getDirectory(context),
            LeastRecentlyUsedCacheEvictor(maxBytes),
            databaseProvider
        )

        val cacheDataSourceFactory =
            CacheDataSource.Factory()
                .setCache(cache)
                .setUpstreamDataSourceFactory(DefaultHttpDataSource.Factory())

        return CacheReel(cache, cacheDataSourceFactory)
    }

    @OptIn(UnstableApi::class)
    fun clearResources(context: Context, cacheReel: CacheReel) {
        CoroutineScope(Dispatchers.IO).launch {
            cacheReel.cache.release()
            getDirectory(context).deleteOnExit()
        }
    }
}
