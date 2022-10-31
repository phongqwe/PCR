package com.qxdzbc.pcr.screen.common

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MFloatButton(
    onClick:()->Unit
) {
    FloatingActionButton(
    onClick = onClick ,
    elevation = FloatingActionButtonDefaults.elevation(10.dp),
    backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            Icons.Filled.Add,
            "",
            tint = MaterialTheme.colors.onPrimary
        )
    }
}
