package com.hajjcompanion.di

import com.hajjcompanion.core.data.rituals.RitualsRepositoryImpl
import com.hajjcompanion.core.domain.rituals.RitualsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRitualsRepository(impl: RitualsRepositoryImpl): RitualsRepository
}
