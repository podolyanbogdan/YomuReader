package com.yomu.domain.repository.model.library.tabs

import androidx.annotation.StringRes
import com.yomu.domain.R

enum class LibraryPagerTabs(@StringRes val tabName: Int) {
    READING(R.string.tab_reading),
    WILL_READ(R.string.tab_will_read),
    READ_IT(R.string.tab_read_it)
}