package com.yomu.library.usecase

import com.yomu.domain.repository.library.LibraryPagerTabsRepository
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabModel
import kotlinx.coroutines.flow.MutableStateFlow

class GetLibraryTabsUseCase(
    private val libraryPagerTabsRepository: LibraryPagerTabsRepository
) {
    fun execute(): MutableStateFlow<List<LibraryPagerTabModel>> {
        return libraryPagerTabsRepository.getLibraryPagerTabs()
    }
}