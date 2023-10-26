package com.yomu.domain.repository.library

import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabModel
import kotlinx.coroutines.flow.MutableStateFlow

fun interface LibraryPagerTabsRepository {
    fun getLibraryPagerTabs(): MutableStateFlow<List<LibraryPagerTabModel>>
}