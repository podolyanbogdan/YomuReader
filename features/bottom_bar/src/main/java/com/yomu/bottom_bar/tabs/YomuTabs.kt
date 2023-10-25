package com.yomu.bottom_bar.tabs


interface YomuTabs {

    val library: YomuTab
    val updates: YomuTab
    val history: YomuTab
    val search: YomuTab
    val more: YomuTab

    fun getTabs(): List<YomuTab>
}