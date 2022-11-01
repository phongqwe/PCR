package com.qxdzbc.pcr.screen.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.state.model.Tag

@Composable
fun TagPickerDialog(
    tags: List<Tag>,
    initSelectedTags: List<Tag>,
    onDone: (selectedTag: List<Tag>) -> Unit,
    onDismiss: () -> Unit,
) {
    var selectedTags: List<Tag> by remember { ms(initSelectedTags) }

    AlertDialog(onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDone(selectedTags)
                onDismiss()
            }) {
                Text("Ok")
            }
        },
        dismissButton = {
            Button(onClick = {
                selectedTags = emptyList()
//                onDone(selectedTags)
//                onDismiss()
            }) {
                Text("Clear all")
            }
        },
        text = {
            val onSelectTag: (Boolean,Tag) -> Unit = remember{
                { s,tag ->
                    if (s) {
                        selectedTags = selectedTags + tag
                    } else {
                        selectedTags = selectedTags - tag
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                this.items(tags) { tag ->
                    val isSelected = tag in selectedTags
                    MRow(modifier = Modifier.clickable {
                        onSelectTag(!isSelected,tag)
                    }) {
                        MCheckbox(checked = isSelected, onCheckedChange = {
                            onSelectTag(it,tag)
                        })
                        Text(tag.name)
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewTagPicker() {
    val tags = (1..100).map {
        DbTag.random()
    }
    TagPickerDialog(
        tags = tags,
        onDone = {},
        initSelectedTags = emptyList(),
        onDismiss = {},
    )
}
