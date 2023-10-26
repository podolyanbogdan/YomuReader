package com.yomu.domain.repository.model.library.filters

import androidx.annotation.StringRes
import com.yomu.domain.R

enum class Filter(@StringRes val filterName: Int) {
    DOWNLOADED(R.string.filter_downloaded),
    UNREAD(R.string.filter_unread),
    STARTED(R.string.filter_started),
    BOOKMARKED(R.string.filter_bookmarked),
    COMPLETED(R.string.filter_completed),
}