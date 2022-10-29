package com.qxdzbc.pcr.screen.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.screen.main_screen.entry_view.SmallTagView
import com.qxdzbc.pcr.state.model.Tag

@Composable
fun TagListView(
    tags:List<Tag>,
    onCloseTag:(Tag)->Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    scrollState:ScrollState = rememberScrollState(),
    ) {
    val spacerWidth = 6.dp
    LazyRow(
        state = lazyListState,
        modifier = modifier
            .scrollable(scrollState, orientation = Orientation.Vertical)
    ) {
        items(tags.withIndex().toList()) { (i, tag) ->
            Box(modifier = Modifier) {
                SmallTagView(tag = tag,onClose={
                    onCloseTag(tag)
                })
            }
            if (i != tags.size - 1) {
                Spacer(modifier = Modifier.width(spacerWidth))
            }
        }
    }
}
