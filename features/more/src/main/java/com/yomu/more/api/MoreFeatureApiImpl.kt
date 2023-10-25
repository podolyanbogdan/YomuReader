package com.yomu.more.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.more.ui.MoreScreen
import com.yomu.more_api.MoreFeatureApi

class MoreFeatureApiImpl : MoreFeatureApi, NavigationFactory {

    override val route = "more_graph"
    private val destination = "more"

    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.navigation(route = route, startDestination = destination) {
            composable(destination) {
                MoreScreen()
            }
        }
    }

}