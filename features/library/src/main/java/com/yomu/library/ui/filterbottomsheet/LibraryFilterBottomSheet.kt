package com.yomu.library.ui.filterbottomsheet

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yomu.core.colors.LocalAppColors
import com.yomu.domain.repository.model.library.filters.Display
import com.yomu.domain.repository.model.library.filters.Filter
import com.yomu.domain.repository.model.library.filters.Sort

@SuppressLint("MutableCollectionMutableState")
@Composable
fun LibraryFilterBottomSheet(
    filterList: Triple<List<Filter>, List<Sort>, List<Display>>,
    onFilterChanged: (Filter) -> Unit,
    onSortChanged: (Sort) -> Unit,
    onDisplayChanged: (Display) -> Unit
) {
    val tabTitles = listOf("Filter", "Sort", "Display")

    val filterItems = filterList.first
    val sortItems = filterList.second
    val displayItems = filterList.third

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var selectedFilter by remember { mutableStateOf(Filter.DOWNLOADED) }
    var selectedSort by remember { mutableStateOf(Sort.ALPHABET) }
    var selectedDisplay by remember { mutableStateOf(Display.COMPACT_GRID) }

    Box(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
    ) {
        Column {
            TabRow(selectedTabIndex) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    ) {
                        Text(title)
                    }
                }
            }

            when (selectedTabIndex) {
                0 -> {
                    LazyColumn {
                        items(filterItems) { item ->
                            CheckboxItem(
                                text = stringResource(id = item.filterName),
                                checked = selectedFilter == item
                            ) {
                                selectedFilter = item
                                onFilterChanged(item)
                            }
                        }
                    }
                }

                1 -> {
                    LazyColumn {
                        items(sortItems) { item ->
                            Text(
                                text = stringResource(id = item.sortName),
                                modifier = Modifier.clickable {
                                    selectedSort = item
                                    onSortChanged(item)
                                }
                            )
                        }
                    }
                }

                2 -> {
                    LazyColumn {
                        items(displayItems) { item ->
                            CheckboxItem(
                                text = stringResource(id = item.displayName),
                                checked = selectedDisplay == item
                            ) {
                                selectedDisplay = item
                                onDisplayChanged(item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CheckboxItem(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = LocalAppColors.current.colorSecondaryVariant,
                uncheckedColor = LocalAppColors.current.colorPrimary
            )
        )
        Text(text = text)
    }
}