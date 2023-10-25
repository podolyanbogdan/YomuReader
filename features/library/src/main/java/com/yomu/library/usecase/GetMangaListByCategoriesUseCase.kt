package com.yomu.library.usecase

import com.yomu.core.model.MockCategories
import com.yomu.core.model.YomuMangaModel
import com.yomu.domain.repository.LibraryRepository
import kotlinx.coroutines.flow.Flow

class GetMangaListByCategoriesUseCase(
    private val repository: LibraryRepository,
) {

    fun execute(category: MockCategories): Flow<List<YomuMangaModel>> {
        return repository.getMangaListByCategory(category)
    }
}