package com.yomu.data.repository.library

import com.yomu.domain.repository.library.LibraryFilterRepository
import com.yomu.domain.repository.model.library.filters.Display
import com.yomu.domain.repository.model.library.filters.Filter
import com.yomu.domain.repository.model.library.filters.Sort
import kotlinx.coroutines.flow.MutableStateFlow

class LibraryFilterRepositoryImpl : LibraryFilterRepository {

    private val filterItems = listOf(
        Filter.DOWNLOADED,
        Filter.UNREAD,
        Filter.STARTED,
        Filter.BOOKMARKED,
        Filter.COMPLETED
    )
    private val sortItems = listOf(
        Sort.ALPHABET,
        Sort.LAST_READ,
        Sort.LAST_UPDATE,
        Sort.UNREAD_COUNT,
        Sort.TOTAL_CHAPTERS,
        Sort.LATEST_CHAPTER,
        Sort.CHAPTER_FETCH_DATE,
        Sort.DATE_ADDED
    )
    private val displayItems = listOf(
        Display.COMPACT_GRID,
        Display.COMFORTABLE_GRID,
        Display.COVER_ONLY_GRID,
        Display.LIST
    )

    private val allLists = Triple(filterItems, sortItems, displayItems)

    override fun getAllFilters(): MutableStateFlow<Triple<List<Filter>, List<Sort>, List<Display>>> {
        return MutableStateFlow(allLists)
    }
}