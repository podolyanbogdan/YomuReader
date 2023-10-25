package com.yomu.more.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoreScreen() {
    MoreScreenContent("More")
}

@Composable
internal fun MoreScreenContent(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        modifier = modifier
    )
}