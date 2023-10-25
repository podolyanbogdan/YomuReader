package com.yomu.updates.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UpdatesScreen() {
    UpdatesScreenContent("Updates")
}

@Composable
internal fun UpdatesScreenContent(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        modifier = modifier
    )
}