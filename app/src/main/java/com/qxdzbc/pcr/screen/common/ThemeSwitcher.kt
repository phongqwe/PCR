package com.qxdzbc.pcr.screen.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ThemeSwitcher(
    isDark:Boolean,
    modifier: Modifier = Modifier,
    switchTheme:(isDark:Boolean)->Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = if (isDark) "dark" else "light",
        )
        Switch(
            checked = isDark,
            onCheckedChange = { newValue ->
                switchTheme(newValue)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.onSurface,
            ),
        )
    }
}
