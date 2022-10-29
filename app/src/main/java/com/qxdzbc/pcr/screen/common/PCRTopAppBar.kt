package com.qxdzbc.pcr.screen.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun PCRTopAppBar(
    content:@Composable ()->Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    ) {
        content()
    }
}
