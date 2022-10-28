package com.qxdzbc.pcr.screen.common

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun StdDivider(modifier:Modifier = Modifier) {
    Divider(thickness = 1.dp,color = Color.LightGray, modifier = modifier)
}
