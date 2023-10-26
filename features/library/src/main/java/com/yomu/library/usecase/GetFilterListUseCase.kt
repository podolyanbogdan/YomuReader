package com.yomu.library.usecase

import com.yomu.domain.repository.library.LibraryFilterRepository
import com.yomu.domain.repository.model.library.filters.Display
import com.yomu.domain.repository.model.library.filters.Filter
import com.yomu.domain.repository.model.library.filters.Sort
import kotlinx.coroutines.flow.MutableStateFlow

class GetFilterListUseCase(
    private val libraryFilterRepository: LibraryFilterRepository
) {
    fun execute(): MutableStateFlow<Triple<List<Filter>, List<Sort>, List<Display>>> {
        return libraryFilterRepository.getAllFilters()
    }
}