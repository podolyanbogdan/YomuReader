package com.yomu.more.di

import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.more.api.MoreFeatureApiImpl
import com.yomu.more_api.MoreFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class MoreModule {

    @IntoSet
    @Provides
    fun provideUpdatesNavigationFactory(): NavigationFactory {
        return MoreFeatureApiImpl()
    }

    @Provides
    fun provideUpdatesFeatureApi(): MoreFeatureApi {
        return MoreFeatureApiImpl()
    }
}