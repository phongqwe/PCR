package com.qxdzbc.pcr.screen.manage_tag_screen

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.screen.common.InputField
import com.qxdzbc.pcr.state.model.Tag

@Composable
fun EditTagDialog(
    oldTag: Tag,
    onDismiss: () -> Unit,
    onDone: (oldTag: Tag, newTag: Tag) -> Unit
) {
    var newTagName by remember { ms(oldTag.name) }
    AlertDialog(onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                val newTag = oldTag.setName(newTagName)
                onDone(oldTag, newTag)
                onDismiss()
            }, enabled = newTagName.isNotEmpty()) {
                Text("Ok")
            }
        },
        text = {
            InputField(title = "Tag name", onValueChange = {
                newTagName = it
            }, value = newTagName, isError = newTagName.isEmpty())
        }
    )
}

@Preview
@Composable
fun PreviewEditTagDialog() {
    EditTagDialog(
        oldTag = DbTag("", ""),
        onDismiss = { /*TODO*/ },
        onDone = { _, _ -> Unit },

        )
}
