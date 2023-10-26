package com.yomu.domain.repository.library

import com.yomu.core.model.YomuMangaModel
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabs
import kotlinx.coroutines.flow.Flow

fun interface LibraryRepository {

    fun getMangaListByCategory(category: LibraryPagerTabs): Flow<List<YomuMangaModel>>
}