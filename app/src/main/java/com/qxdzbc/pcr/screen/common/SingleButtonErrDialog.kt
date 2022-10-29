package com.qxdzbc.pcr.screen.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SingleButtonErrDialog(
    onDismiss:()->Unit,
    onOk:()->Unit,
    text:String,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onOk) {
                Text("Ok")
            }
        },
        text = {
            Text(text)
        },
    )
}
