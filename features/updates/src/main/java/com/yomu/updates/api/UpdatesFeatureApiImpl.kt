package com.yomu.updates.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.updates.ui.UpdatesScreen
import com.yomu.updates_api.UpdatesFeatureApi

class UpdatesFeatureApiImpl: UpdatesFeatureApi, NavigationFactory {

    override val route = "updates_graph"
    private val destination = "updates"

    override fun create(builder: NavGraphBuilder, navController: NavHostController) {
        builder.navigation(route = route, startDestination = destination) {
            composable(destination) {
                UpdatesScreen()
            }
        }
    }
}