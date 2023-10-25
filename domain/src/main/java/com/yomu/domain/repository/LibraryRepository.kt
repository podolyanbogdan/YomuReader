package com.yomu.domain.repository

import com.yomu.core.model.MockCategories
import com.yomu.core.model.YomuMangaModel
import kotlinx.coroutines.flow.Flow

interface LibraryRepository {

    fun getMangaListByCategory(category: MockCategories): Flow<List<YomuMangaModel>>
}