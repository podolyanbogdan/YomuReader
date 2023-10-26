package com.yomu.domain.repository.library

import com.yomu.domain.repository.model.library.filters.Display
import com.yomu.domain.repository.model.library.filters.Filter
import com.yomu.domain.repository.model.library.filters.Sort
import kotlinx.coroutines.flow.MutableStateFlow

fun interface LibraryFilterRepository {
    fun getAllFilters(): MutableStateFlow<Triple<List<Filter>, List<Sort>, List<Display>>>
}