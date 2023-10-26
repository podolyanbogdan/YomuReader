package com.yomu.library.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yomu.core.model.YomuMangaModel
import com.yomu.core.ui.composable.YomuMangaItem
import com.yomu.core.ui.error.ErrorBlock
import com.yomu.domain.repository.model.library.filters.Display
import com.yomu.domain.repository.model.library.filters.Filter
import com.yomu.domain.repository.model.library.filters.Sort
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabModel
import com.yomu.domain.repository.model.library.tabs.LibraryPagerTabs
import com.yomu.library.R
import com.yomu.library.ui.filterbottomsheet.LibraryFilterBottomSheet
import com.yomu.library.ui.toolbar.LibraryToolbar
import kotlinx.coroutines.launch

@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LibraryScreenContent(
        toolbarTitle = stringResource(id = R.string.toolbar_title),
        searchText = state.searchText,
        onSearchScreensChange = viewModel::onSearchScreensChange,
        onSearchModeClosed = viewModel::onSearchModeClosed,
        tabList = state.tabsList,
        onTabSelected = viewModel::onTabSelected,
        mangaList = state.mangaList,
        filterList = state.filterList,
        onFilterChanged = viewModel::onFilterChanged,
        onSortChanged = viewModel::onSortChanged,
        onDisplayChanged = viewModel::onDisplayChanged
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
internal fun LibraryScreenContent(
    modifier: Modifier = Modifier,
    toolbarTitle: String,
    searchText: String,
    onSearchScreensChange: (String) -> Unit = {},
    onSearchModeClosed: () -> Unit = {},
    tabList: List<LibraryPagerTabModel>,
    onTabSelected: (LibraryPagerTabs) -> Unit,
    mangaList: List<YomuMangaModel>,
    filterList: Triple<List<Filter>, List<Sort>, List<Display>>,
    onFilterChanged: (Filter) -> Unit,
    onSortChanged: (Sort) -> Unit,
    onDisplayChanged: (Display) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { tabList.size })
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { newPage ->
                onTabSelected.invoke(tabList[newPage].category)
            }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            LibraryFilterBottomSheet(
                filterList = filterList,
                onFilterChanged = { onFilterChanged.invoke(it) },
                onSortChanged = { onSortChanged.invoke(it) },
                onDisplayChanged = { onDisplayChanged.invoke(it) }
            )
        }
    ) {
        Column(
            modifier = modifier
        ) {
            LibraryToolbar(
                title = toolbarTitle,
                searchText = searchText,
                onSearchScreensChange = onSearchScreensChange,
                onSearchModeClosed = { onSearchModeClosed.invoke() },
                onFilterClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabList.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(stringResource(id = title.category.tabName)) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }
                            onTabSelected.invoke(title.category)
                            onSearchModeClosed.invoke()
                        },
                    )
                }
            }

            HorizontalPager(state = pagerState) {
                if (mangaList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        ErrorBlock(
                            title = stringResource(id = R.string.nothing_found),
                            description = stringResource(id = R.string.please_try_again),
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(mangaList) { item ->
                            YomuMangaItem(item)
                        }
                    }
                }
            }
        }
    }
}
