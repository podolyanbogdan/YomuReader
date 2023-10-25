package com.yomu.search.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.search.ui.SearchScreen
import com.yomu.search_api.SearchFeatureApi

class SearchFeatureApiImpl: SearchFeatureApi, NavigationFactory {

    override val route = "search_graph"
    private val destination = "search"

    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.navigation(route = route, startDestination = destination) {
            composable(destination) {
                SearchScreen()
            }
        }
    }
}