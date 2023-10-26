package com.yomu.yomureader.main

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.yomu.bottom_bar.ui.YomuNavigationBar
import com.yomu.core.ui.navigation.NavigationFactory
import com.yomu.core.ui.theme.YomuReaderTheme
import com.yomu.library_api.LibraryFeatureApi
import com.yomu.yomureader.graph.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var libraryFeatureApi: LibraryFeatureApi

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val window: Window = this.window
            window.navigationBarColor =
                resources.getColor(com.yomu.core.R.color.colorYomuNavigationBackground, null)

            YomuReaderTheme {
                Scaffold(
                    bottomBar = {
                        YomuNavigationBar(
                            navController = navController,
                            viewModel = hiltViewModel()
                        )
                    }
                ) {
                    AppNavGraph(
                        navController = navController,
                        navigationFactories = navigationFactories,
                        startDestination = libraryFeatureApi.route,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}