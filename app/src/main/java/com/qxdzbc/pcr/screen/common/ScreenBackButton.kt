package com.qxdzbc.pcr.screen.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun ScreenBackButton(back: () -> Unit) {
    MIconButton(
        imageVector = Icons.Default.ArrowBack,
        onClick = back
    )
}
