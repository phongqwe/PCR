package com.qxdzbc.pcr.screen.main_screen.entry_view

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.screen.common.StdDivider
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.ui.theme.PCRTheme

@Composable
fun EntryView(
    entry: Entry,
    uploadEntry: () -> Unit,
    modifier: Modifier = Modifier,
    cardSpacing: Int = 5,
) {
    val crScope = rememberCoroutineScope()
    val tagListState = rememberLazyListState()
    Card(
        modifier = modifier.padding(bottom=cardSpacing.dp),
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
                if (entry.isUploaded.not()) {

                    IconButton(
                        onClick = {
                            uploadEntry()
                        },
                        modifier = Modifier.constrainAs(warning) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Warning,
                            contentDescription = null,
                            tint = Color.Red,
                        )

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
            val tagListPadding = 0.dp
            val spacerWidth = 6.dp
            LazyRow(
                state = tagListState,
                modifier = Modifier
                    .scrollable(tagRowScrollState, orientation = Orientation.Vertical)
                    .padding(start = tagListPadding)
            ) {
                items(entry.tags.withIndex().toList()) { (i, tag) ->
                    Box(modifier = Modifier) {
                        SmallTagView(tag = tag)
                    }
                    if (i != entry.tags.size - 1) {
                        Spacer(modifier = Modifier.width(spacerWidth))
                    }
                }
            }
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
