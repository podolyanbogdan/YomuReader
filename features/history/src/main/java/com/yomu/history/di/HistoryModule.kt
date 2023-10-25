package com.yomu.history.di

import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.history.api.HistoryFeatureApiImpl
import com.yomu.history_api.HistoryFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class HistoryModule {

    @IntoSet
    @Provides
    fun provideHistoryNavigationFactory(): NavigationFactory {
        return HistoryFeatureApiImpl()
    }

    @Provides
    fun provideHistoryFeatureApi(): HistoryFeatureApi {
        return HistoryFeatureApiImpl()
    }
}