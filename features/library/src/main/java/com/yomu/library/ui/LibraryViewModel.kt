package com.yomu.library.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yomu.core.model.YomuMangaModel
import com.yomu.domain.repository.model.library.filters.Display
import com.yomu.domain.repository.model.library.filters.Filter
import com.yomu.domain.repository.model.library.filters.Sort
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabs
import com.yomu.library.model.LibraryScreenModel
import com.yomu.library.usecase.GetFilterListUseCase
import com.yomu.library.usecase.GetLibraryTabsUseCase
import com.yomu.library.usecase.GetMangaListByCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class LibraryViewModel @Inject constructor(
    private val getMangaListByCategoriesUseCase: GetMangaListByCategoriesUseCase,
    private val getFilterListUseCase: GetFilterListUseCase,
    private val getLibraryTabsUseCase: GetLibraryTabsUseCase
) : ViewModel() {

    private var mangaFlowJob: Job? = null
    private var filterFlowJob: Job? = null
    private var tabsFlowJob: Job? = null
    private var originalMangaList = listOf<YomuMangaModel>()

    private val _state = MutableStateFlow(LibraryScreenModel())
    val state = _state.asStateFlow()

    init {
        getTabList()
        getFilterList()
        getMangaListByCategory(_state.value.tabsList.first().category)
    }

    private fun getMangaListByCategory(category: LibraryPagerTabs) {
        mangaFlowJob?.cancel()
        mangaFlowJob = viewModelScope.launch {
            getMangaListByCategoriesUseCase.execute(category)
                .cancellable()
                .collect {
                    originalMangaList = it
                    _state.emit(state.value.copy(mangaList = it))
                }
        }
    }

    private fun getFilterList() {
        filterFlowJob?.cancel()
        filterFlowJob = viewModelScope.launch {
            getFilterListUseCase.execute()
                .collect {
                    _state.emit(state.value.copy(filterList = it))
                }
        }
    }

    private fun getMangaListByText(text: String) {
        mangaFlowJob = viewModelScope.launch {
            val currentState = _state.value

            val filteredList: List<YomuMangaModel> = if (text.isEmpty()) {
                originalMangaList
            } else {
                originalMangaList.filter { it.name.contains(text, ignoreCase = true) }
            }

            mangaFlowJob?.cancel()
            _state.emit(currentState.copy(mangaList = filteredList))
        }
    }

    private fun getTabList() {
        tabsFlowJob?.cancel()
        tabsFlowJob = viewModelScope.launch {
            getLibraryTabsUseCase.execute()
                .collect {
                    _state.emit(state.value.copy(tabsList = it))
                }
        }
    }

    fun onSearchScreensChange(text: String) {
        viewModelScope.launch {
            _state.update { it.copy(searchText = text) }
            getMangaListByText(text)
        }
    }

    fun onSearchModeClosed() {
        _state.update { it.copy(searchText = "") }
        val currentState = _state.value
        mangaFlowJob?.cancel()
        mangaFlowJob = viewModelScope.launch {
            _state.emit(currentState.copy(mangaList = originalMangaList))
        }
    }

    fun onTabSelected(category: LibraryPagerTabs) {
        getMangaListByCategory(category)
    }

    fun onFilterChanged(filter: Filter) {
        Log.d("test", "$filter")
    }

    fun onSortChanged(sort: Sort) {
        Log.d("test", "$sort")
    }

    fun onDisplayChanged(display: Display) {
        Log.d("test", "$display")
    }
}