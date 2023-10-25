package com.yomu.data.di

import com.yomu.data.repository.LibraryRepositoryImpl
import com.yomu.domain.repository.LibraryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideLibraryRepositoryImpl(
    ): LibraryRepository {
        return LibraryRepositoryImpl()
    }
}