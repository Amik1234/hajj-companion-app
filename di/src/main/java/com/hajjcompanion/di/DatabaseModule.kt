package com.hajjcompanion.di

import android.content.Context
import androidx.room.Room
import com.hajjcompanion.core.data.rituals.db.AppDatabase
import com.hajjcompanion.core.data.rituals.db.RitualDao
import com.hajjcompanion.core.data.rituals.db.RitualProgressDao
import com.hajjcompanion.core.data.rituals.db.RitualStepDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "hajj_companion.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRitualDao(db: AppDatabase): RitualDao = db.ritualDao()

    @Provides
    fun provideRitualStepDao(db: AppDatabase): RitualStepDao = db.ritualStepDao()

    @Provides
    fun provideRitualProgressDao(db: AppDatabase): RitualProgressDao = db.ritualProgressDao()
}
