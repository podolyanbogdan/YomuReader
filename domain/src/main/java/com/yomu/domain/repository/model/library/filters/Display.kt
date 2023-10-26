package com.yomu.domain.repository.model.library.filters

import androidx.annotation.StringRes
import com.yomu.domain.R

enum class Display(@StringRes val displayName: Int) {
    COMPACT_GRID(R.string.display_compact_grid),
    COMFORTABLE_GRID(R.string.display_comfortable_grid),
    COVER_ONLY_GRID(R.string.display_cover_only_grid),
    LIST(R.string.display_list)
}