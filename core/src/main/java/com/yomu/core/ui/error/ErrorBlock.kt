package com.yomu.core.ui.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yomu.core.R

@Composable
fun ErrorBlock(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    showTryAgain: Boolean = false,
    onTryAgainClick: () -> Unit = { },
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_book),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = description, textAlign = TextAlign.Center)
        if (showTryAgain) {
            TextButton(onClick = onTryAgainClick) {
                Text(text = stringResource(R.string.try_again).uppercase())
            }
        }
    }

}