package com.yomu.core.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController


fun interface NavigationFactory {
    fun create(builder: NavGraphBuilder, navController: NavHostController)
}