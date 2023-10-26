package com.yomu.library.model

import com.yomu.core.model.YomuMangaModel
import com.yomu.domain.repository.model.library.filters.Display
import com.yomu.domain.repository.model.library.filters.Filter
import com.yomu.domain.repository.model.library.filters.Sort
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabModel

data class LibraryScreenModel(
    val searchText: String = "",
    val isEditabledState: Boolean = false,
    var tabsList: List<LibraryPagerTabModel> = listOf(),
    var mangaList: List<YomuMangaModel> = listOf(),
    var searchMangaList: List<YomuMangaModel> = listOf(),
    var filterList: Triple<List<Filter>, List<Sort>, List<Display>> = Triple(
        listOf(),
        listOf(),
        listOf()
    )
)
