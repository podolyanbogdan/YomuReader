package com.yomu.library.api

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.library.ui.LibraryScreen
import com.yomu.library.ui.LibraryViewModel
import com.yomu.library_api.LibraryFeatureApi

class LibraryFeatureApiImpl : LibraryFeatureApi, NavigationFactory {
    override val route: String = "library_graph"
    private val destination = "library"

    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.navigation(route = route, startDestination = destination) {
            composable(destination) {
                val libraryViewModel: LibraryViewModel = hiltViewModel()
                LibraryScreen(libraryViewModel)
            }
        }
    }
}