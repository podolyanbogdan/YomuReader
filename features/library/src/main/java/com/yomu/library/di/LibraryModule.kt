package com.yomu.library.di

import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.domain.repository.library.LibraryFilterRepository
import com.yomu.domain.repository.library.LibraryPagerTabsRepository
import com.yomu.domain.repository.library.LibraryRepository
import com.yomu.library.api.LibraryFeatureApiImpl
import com.yomu.library.usecase.GetFilterListUseCase
import com.yomu.library.usecase.GetLibraryTabsUseCase
import com.yomu.library.usecase.GetMangaListByCategoriesUseCase
import com.yomu.library_api.LibraryFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class LibraryModule {

    @IntoSet
    @Provides
    fun provideNavigationFactory(): NavigationFactory {
        return LibraryFeatureApiImpl()
    }

    @Provides
    fun provideLibraryFeatureApi(): LibraryFeatureApi {
        return LibraryFeatureApiImpl()
    }

    @Provides
    fun provideGetMangaListByCategoriesUseCase(
        camsRepository: LibraryRepository,
    ): GetMangaListByCategoriesUseCase {
        return GetMangaListByCategoriesUseCase(camsRepository)
    }

    @Provides
    fun provideGetFilterListUseCase(
        libraryFilterRepository: LibraryFilterRepository,
    ): GetFilterListUseCase {
        return GetFilterListUseCase(libraryFilterRepository)
    }

    @Provides
    fun provideLibraryTabsUseCase(
        libraryPagerTabsRepository: LibraryPagerTabsRepository,
    ): GetLibraryTabsUseCase {
        return GetLibraryTabsUseCase(libraryPagerTabsRepository)
    }
}