package com.yomu.bottom_bar.ui

import android.graphics.drawable.AnimatedVectorDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yomu.bottom_bar.R
import com.yomu.bottom_bar.model.YomuBottomNavigationModel
import com.yomu.bottom_bar.tabs.YomuTab
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

@Composable
fun YomuNavigationBar(
    navController: NavController,
    viewModel: YomuBottomNavigationViewModel,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry) {
        navBackStackEntry?.let {
            viewModel.onNavDestinationChanges(it.destination)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvents
            .shareIn(this, SharingStarted.Lazily)
            .collect { tab ->
                navController.navigate(tab.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
    }

    BottomBarContent(
        state = state,
        tabs = viewModel.bottomTabs.getTabs(),
        onTabClick = viewModel::onTabClick
    )
}

@Composable
private fun BottomBarContent(
    state: YomuBottomNavigationModel,
    tabs: List<YomuTab>,
    modifier: Modifier = Modifier,
    onTabClick: (YomuTab) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {

            tabs.forEach { tab ->
                Tab(
                    tab = tab,
                    selected = state.selectedTab == tab,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    onClick = { onTabClick(tab) }
                )
            }
        }
    }
}

@Composable
private fun Tab(
    tab: YomuTab,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        color = colorResource(id = R.color.colorYomuNavigationBackground),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            SetIconWithBackground(tab = tab, isSelected = selected)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = tab.title),
                color = colorResource(R.color.colorPrimary),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
private fun SetIconWithBackground(tab: YomuTab, isSelected: Boolean) {
    if (isSelected) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .background(
                    color = colorResource(id = R.color.colorYomuNavigationSelectedIcon),
                    shape = RoundedCornerShape(50)
                )
        ) {
            Layout(
                content = { AnimatedIcon(tab.iconActive) },
                modifier = Modifier
            ) { measurables, constraints ->
                val placeable = measurables.firstOrNull()?.measure(constraints)
                val width = placeable?.width ?: 0
                val height = placeable?.height?.plus(5.dp.roundToPx()) ?: 0
                layout(width, height) {
                    placeable?.placeRelative(0, 2.5.dp.roundToPx())
                }
            }
        }
    } else {
        AnimatedIcon(tab.iconInactive)
    }
}

@Composable
fun AnimatedIcon(@DrawableRes id: Int) {
    val animatedVector = LocalContext.current.getDrawable(id) as? AnimatedVectorDrawable

    AndroidView(
        factory = {
            ImageView(it).apply {
                setImageDrawable(animatedVector)
            }
        },
        update = {
            animatedVector?.start()
        }
    )
}