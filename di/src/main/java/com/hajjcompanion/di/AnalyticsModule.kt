package com.hajjcompanion.di

import com.hajjcompanion.core.common.analytics.Analytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {
    @Provides
    @Singleton
    fun provideAnalytics(): Analytics = object : Analytics {
        override fun logEvent(name: String, params: Map<String, Any?>) { /* no-op */ }
    }
}
