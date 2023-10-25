package com.yomu.bottom_bar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.yomu.bottom_bar.model.YomuBottomNavigationModel
import com.yomu.bottom_bar.tabs.YomuTabs
import com.yomu.bottom_bar.tabs.YomuTab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YomuBottomNavigationViewModel @Inject constructor(
    val bottomTabs: YomuTabs
) : ViewModel()  {

    private val _state = MutableStateFlow(YomuBottomNavigationModel(selectedTab = bottomTabs.library))
    val state = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<YomuTab>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    fun onNavDestinationChanges(navDestination: NavDestination) {
        viewModelScope.launch {
            val selectedRoute = navDestination.hierarchy
                .map { it.route }
                .firstOrNull { route ->
                    route in bottomTabs.getTabs().map { it.route }
                }

            bottomTabs.getTabs().firstOrNull { it.route == selectedRoute }?.let {
                selectTab(it)
            }
        }
    }

    private fun selectTab(tab: YomuTab) {
        viewModelScope.launch {
            _state.emit(state.value.copy(selectedTab = tab))
        }
    }

    fun onTabClick(tab: YomuTab) {
        viewModelScope.launch {
            if (tab == state.value.selectedTab) {
                return@launch
            }
            _navigationEvents.emit(tab)

            selectTab(tab)
        }
    }

}