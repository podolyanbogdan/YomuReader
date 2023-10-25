package com.yomu.library.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yomu.core.model.MockCategories
import com.yomu.core.model.YomuMangaModel
import com.yomu.library.model.LibraryPagerTabModel
import com.yomu.library.model.LibraryScreenModel
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
) : ViewModel() {

    private var mangaFlowJob: Job? = null
    private var originalMangaList = listOf<YomuMangaModel>()

    private val _state = MutableStateFlow(LibraryScreenModel())
    val state = _state.asStateFlow()


    init {
        getTabList()
        getMangaListByCategory(_state.value.tabsList.first().category)
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

    fun onTabSelected(category: MockCategories) {
        getMangaListByCategory(category)
    }

    private fun getMangaListByCategory(category: MockCategories) {
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

    private fun getMangaListByText(text: String) {
        val currentState = _state.value

        val filteredList = if (text.isEmpty()) {
            originalMangaList
        } else {
            originalMangaList.filter { it.name.contains(text, ignoreCase = true) }
        }

        mangaFlowJob?.cancel()
        mangaFlowJob = viewModelScope.launch {
            _state.emit(currentState.copy(mangaList = filteredList))
        }
    }

    private fun getTabList() {
        _state.value.tabsList = listOf(
            LibraryPagerTabModel(MockCategories.READING, "Reading"),
            LibraryPagerTabModel(MockCategories.WILL_READ, "Will read"),
            LibraryPagerTabModel(MockCategories.READ_IT, "Read it")
        )
    }
}