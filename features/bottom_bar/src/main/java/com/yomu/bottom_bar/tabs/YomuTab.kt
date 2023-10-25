package com.yomu.bottom_bar.tabs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface YomuTab {
    @get:StringRes
    val title: Int

    @get:DrawableRes
    val iconActive:Int

    @get:DrawableRes
    val iconInactive: Int

    val route: String
}