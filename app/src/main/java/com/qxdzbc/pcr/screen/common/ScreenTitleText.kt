package com.qxdzbc.pcr.screen.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ScreenTitleText(
    text:String,
) {
    Text(text, fontSize= MaterialTheme.typography.h6.fontSize, fontWeight= MaterialTheme.typography.h6.fontWeight, color= MaterialTheme.colors.onPrimary)
}
