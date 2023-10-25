package com.yomu.library.model

import com.yomu.core.model.MockCategories

data class LibraryPagerTabModel(
    val category: MockCategories,
    val name: String = "",
)
