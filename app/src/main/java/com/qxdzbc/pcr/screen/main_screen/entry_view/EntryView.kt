package com.qxdzbc.pcr.screen.main_screen.entry_view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.R
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.screen.common.MIconButton
import com.qxdzbc.pcr.screen.common.StdDivider
import com.qxdzbc.pcr.screen.common.TagListView
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.WriteState
import com.qxdzbc.pcr.ui.theme.PCRTheme

@Composable
fun EntryView(
    entry: Entry,
    uploadEntry: (Entry) -> Unit,
    modifier: Modifier = Modifier,
    cardSpacing: Int = 5,
) {
    val crScope = rememberCoroutineScope()
    Card(
        modifier = modifier.padding(bottom = cardSpacing.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val dateText = createRef()
                val warning = createRef()
                Text(entry.displayDate, modifier = Modifier.constrainAs(dateText) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
                MBox(modifier = Modifier.constrainAs(warning) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }) {
                    when(entry.writeState){
                        WriteState.OK->{
                            Icon(
                                painter = painterResource(R.drawable.cloud_done_24),
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary,
                            )
                        }
                        WriteState.WritePending->{
                            MIconButton(
                                painter = painterResource(R.drawable.backup_icon_24),
                                onClick = { uploadEntry(entry) },
                                tint=Color.Red
                            )
                        }
                    }
                }
            }

            StdDivider()

            if (entry.detail != null) {
                Text(
                    text = entry.detail ?: "",
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            }
            Text(
                text = entry.displayMoney,
                fontSize = MaterialTheme.typography.body1.fontSize
            )

            StdDivider(modifier = Modifier.padding(bottom = 5.dp))

            val tagRowScrollState = rememberScrollState(0)
            val tagListState = rememberLazyListState()

            TagListView(
                entry.tags,
                lazyListState = tagListState,
                scrollState = tagRowScrollState,
                onCloseTag = {
                    // TODO
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEntryView() {
    PCRTheme(darkTheme = true) {
        Surface() {
            EntryView(
                entry = DbEntryWithTags.random(),
                uploadEntry = {},
                modifier = Modifier.padding(5.dp)
            )
        }
    }

}
