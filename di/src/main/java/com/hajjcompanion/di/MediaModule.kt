package com.hajjcompanion.di

import com.hajjcompanion.core.common.media.OfflineMediaManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaModule {
    @Provides
    @Singleton
    fun provideOfflineMediaManager(): OfflineMediaManager = object : OfflineMediaManager {
        override suspend fun download(url: String) = Result.success(url)
        override suspend fun isDownloaded(url: String) = false
        override suspend fun remove(url: String) {}
    }
}
