package com.yomu.library.usecase

import com.yomu.core.model.YomuMangaModel
import com.yomu.domain.repository.library.LibraryRepository
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabs
import kotlinx.coroutines.flow.Flow

class GetMangaListByCategoriesUseCase(
    private val repository: LibraryRepository,
) {

    fun execute(category: LibraryPagerTabs): Flow<List<YomuMangaModel>> {
        return repository.getMangaListByCategory(category)
    }
}