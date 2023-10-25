package com.yomu.bottom_bar.di

import com.yomu.bottom_bar.tabs.YomuTabs
import com.yomu.bottom_bar.tabs.YomuTabsImpl
import com.yomu.history_api.HistoryFeatureApi
import com.yomu.library_api.LibraryFeatureApi
import com.yomu.more_api.MoreFeatureApi
import com.yomu.search_api.SearchFeatureApi
import com.yomu.updates_api.UpdatesFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class YomuBottomNavigationModule {

    @Provides
    fun provideBottomBarTabs(
        libraryFeatureApi: LibraryFeatureApi,
        updatesFeatureApi: UpdatesFeatureApi,
        historyFeatureApi: HistoryFeatureApi,
        searchFeatureApi: SearchFeatureApi,
        moreFeatureApi: MoreFeatureApi
    ): YomuTabs {
        return YomuTabsImpl(libraryFeatureApi, updatesFeatureApi, historyFeatureApi, searchFeatureApi, moreFeatureApi)
    }
}