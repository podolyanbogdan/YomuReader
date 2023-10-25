package com.yomu.search.di

import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.search.api.SearchFeatureApiImpl
import com.yomu.search_api.SearchFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @IntoSet
    @Provides
    fun provideSearchNavigationFactory(): NavigationFactory {
        return SearchFeatureApiImpl()
    }

    @Provides
    fun provideSearchFeatureApi(): SearchFeatureApi {
        return SearchFeatureApiImpl()
    }
}