package com.qxdzbc.pcr.screen.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.state.model.Tag

@Composable
fun TagPickerDialog(
    tags: List<Tag>,
    initSelectedList:List<Tag>,
    onDone: (selectedTag:List<Tag>) -> Unit,
    onDismiss: () -> Unit,
) {
    val selectionMs = remember { ms(initSelectedList) }
    val selectedTags: List<Tag> by selectionMs
    AlertDialog(onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDone(selectionMs.value)
                onDismiss()
            }) {
                Text("Ok")
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                this.items(tags) { tag ->
                    MRow(modifier = Modifier.clickable {

                    }) {
                        Checkbox(checked = tag in selectedTags, onCheckedChange = {
                            if(it){
                                selectionMs.value = selectionMs.value + tag
                            }else{
                                selectionMs.value = selectionMs.value - tag
                            }
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
        initSelectedList = emptyList(),
        onDismiss = {},
    )
}
