package com.yomu.bottom_bar.tabs

import com.yomu.bottom_bar.R
import com.yomu.history_api.HistoryFeatureApi
import com.yomu.library_api.LibraryFeatureApi
import com.yomu.more_api.MoreFeatureApi
import com.yomu.search_api.SearchFeatureApi
import com.yomu.updates_api.UpdatesFeatureApi


class YomuTabsImpl(
    private val libraryFeatureApi: LibraryFeatureApi,
    private val updatesFeatureApi: UpdatesFeatureApi,
    private val historyFeatureApi: HistoryFeatureApi,
    private val searchFeatureApi: SearchFeatureApi,
    private val moreFeatureApi: MoreFeatureApi
) : YomuTabs {

    override val library = object : YomuTab {
        override val title = R.string.library_title
        override val iconActive = R.drawable.ic_library_tab_enter
        override val iconInactive = R.drawable.ic_library_tab_exit
        override val route = libraryFeatureApi.route
    }
    override val updates = object : YomuTab {
        override val title = R.string.updates_title
        override val iconActive = R.drawable.ic_updates_tab_enter
        override val iconInactive = R.drawable.ic_updates_tab_exit
        override val route = updatesFeatureApi.route
    }
    override val history = object : YomuTab {
        override val title = R.string.history_title
        override val iconActive = R.drawable.ic_history_tab_enter
        override val iconInactive = R.drawable.ic_history_tab_exit
        override val route = historyFeatureApi.route
    }
    override val search = object : YomuTab {
        override val title = R.string.search_title
        override val iconActive = R.drawable.ic_search_tab_enter
        override val iconInactive = R.drawable.ic_search_tab_exit
        override val route = searchFeatureApi.route
    }
    override val more = object : YomuTab {
        override val title = R.string.more_title
        override val iconActive = R.drawable.ic_more_tab_enter
        override val iconInactive = R.drawable.ic_more_tab_exit
        override val route = moreFeatureApi.route
    }

    override fun getTabs() = listOf(
        library, updates, history, search, more
    )
}