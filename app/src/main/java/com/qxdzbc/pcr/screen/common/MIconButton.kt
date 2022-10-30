package com.qxdzbc.pcr.screen.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter

@Composable
fun MIconButton(
    imageVector: ImageVector,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    tint:Color = MaterialTheme.colors.onPrimary,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector=imageVector,
            contentDescription = null,
            tint = tint,
        )
    }
}

@Composable
fun MIconButton(
    painter: Painter,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    tint:Color = MaterialTheme.colors.onPrimary,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            painter=painter,
            contentDescription = null,
            tint = tint,
        )
    }
}
