package com.hajjcompanion.di

import com.hajjcompanion.core.common.notify.ReminderScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReminderModule {
    @Provides
    @Singleton
    fun provideReminderScheduler(): ReminderScheduler = object : ReminderScheduler {
        override fun schedule(id: String, at: java.time.Instant, title: String, body: String) {}
        override fun cancel(id: String) {}
    }
}
