package com.yomu.library.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yomu.core.model.MockCategories
import com.yomu.core.model.YomuMangaModel
import com.yomu.core.ui.composable.YomuMangaItem
import com.yomu.library.R
import com.yomu.library.model.LibraryPagerTabModel
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
        mangaList = state.mangaList
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun LibraryScreenContent(
    modifier: Modifier = Modifier,
    toolbarTitle: String,
    searchText: String,
    onSearchScreensChange: (String) -> Unit = {},
    onSearchModeClosed: () -> Unit = {},
    tabList: List<LibraryPagerTabModel>,
    onTabSelected: (MockCategories) -> Unit,
    mangaList: List<YomuMangaModel>
) {
    val pagerState = rememberPagerState(pageCount = { tabList.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { newPage ->
                onTabSelected.invoke(tabList[newPage].category)
            }
    }

    Column(
        modifier = modifier
    ) {
        LibraryToolbar(
            title = toolbarTitle,
            searchText = searchText,
            onSearchScreensChange = onSearchScreensChange,
            onSearchModeClosed = { onSearchModeClosed.invoke() }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {
            tabList.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title.name) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                        onTabSelected.invoke(title.category)
                    },
                )
            }
        }

        HorizontalPager(state = pagerState) {
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
