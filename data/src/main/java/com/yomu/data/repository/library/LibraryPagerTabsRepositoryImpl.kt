package com.yomu.data.repository.library

import com.yomu.domain.repository.library.LibraryPagerTabsRepository
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabModel
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabs
import kotlinx.coroutines.flow.MutableStateFlow

class LibraryPagerTabsRepositoryImpl : LibraryPagerTabsRepository {

    private val tabs = listOf(
        LibraryPagerTabModel(LibraryPagerTabs.READING),
        LibraryPagerTabModel(LibraryPagerTabs.WILL_READ),
        LibraryPagerTabModel(LibraryPagerTabs.READ_IT),
    )

    override fun getLibraryPagerTabs(): MutableStateFlow<List<LibraryPagerTabModel>> {
        return MutableStateFlow(tabs)
    }
}