package com.yomu.updates.di

import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.updates.api.UpdatesFeatureApiImpl
import com.yomu.updates_api.UpdatesFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class UpdatesModule {

    @IntoSet
    @Provides
    fun provideUpdatesNavigationFactory(): NavigationFactory {
        return UpdatesFeatureApiImpl()
    }

    @Provides
    fun provideUpdatesFeatureApi(): UpdatesFeatureApi {
        return UpdatesFeatureApiImpl()
    }
}