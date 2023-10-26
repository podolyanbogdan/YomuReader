package com.yomu.data.di

import com.yomu.data.repository.library.LibraryFilterRepositoryImpl
import com.yomu.data.repository.library.LibraryPagerTabsRepositoryImpl
import com.yomu.data.repository.library.LibraryRepositoryImpl
import com.yomu.domain.repository.library.LibraryFilterRepository
import com.yomu.domain.repository.library.LibraryPagerTabsRepository
import com.yomu.domain.repository.library.LibraryRepository
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
    fun provideLibraryRepositoryImpl(): LibraryRepository {
        return LibraryRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLibraryFilterRepositoryImpl(): LibraryFilterRepository {
        return LibraryFilterRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLibraryPagerTabsRepositoryImpl(
    ): LibraryPagerTabsRepository {
        return LibraryPagerTabsRepositoryImpl()
    }
}