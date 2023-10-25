package com.yomu.library.model

import com.yomu.core.model.YomuMangaModel

data class LibraryScreenModel(
    val searchText: String = "",
    val isEditabledState: Boolean = false,
    var tabsList: List<LibraryPagerTabModel> = listOf(),
    var mangaList: List<YomuMangaModel> = listOf(),
    var searchMangaList: List<YomuMangaModel> = listOf(),
)
