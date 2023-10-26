package com.yomu.domain.repository.model.library.filters

import androidx.annotation.StringRes
import com.yomu.domain.R

enum class Sort(@StringRes val sortName: Int) {
    ALPHABET(R.string.sort_alphabetcally),
    LAST_READ(R.string.sort_last_read),
    LAST_UPDATE(R.string.sort_last_update),
    UNREAD_COUNT(R.string.filter_unread),
    TOTAL_CHAPTERS(R.string.sort_total_chapters),
    LATEST_CHAPTER(R.string.sort_latest_chapter),
    CHAPTER_FETCH_DATE(R.string.sort_chapter_fetch_date),
    DATE_ADDED(R.string.sort_date_added),
}