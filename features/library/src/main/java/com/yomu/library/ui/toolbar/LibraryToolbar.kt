package com.yomu.library.ui.toolbar

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.yomu.core.ui.composable.BaseYomuSearchToolbar
import com.yomu.core.ui.composable.BaseYomuToolbar
import com.yomu.library.R

@Composable
fun LibraryToolbar(
    title: String,
    searchText: String,
    onSearchScreensChange: (String) -> Unit = {},
    onSearchModeClosed: () -> Unit = {},
) {
    var isSearching by remember { mutableStateOf(false) }

    Crossfade(
        targetState = isSearching,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        label = ""
    ) { currentIsSearching ->
        if (!currentIsSearching) {
            BaseYomuToolbar(title = title) {
                IconButton(onClick = { isSearching = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sort),
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = null
                    )
                }
            }
        } else {
            BaseYomuSearchToolbar(
                searchText = searchText,
                onSearchTextChanged = onSearchScreensChange,
                placeholderText = "Search",
                onBackClick = {
                    isSearching = false
                    onSearchModeClosed.invoke()
                }
            )
        }
    }
}