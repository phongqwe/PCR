package com.qxdzbc.pcr.screen.main_screen.date_filter_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import com.qxdzbc.pcr.util.DateUtils
import java.util.*

@Composable
fun DateView(
    entryFilter: EntryFilter,
    fromDateClick: () -> Unit,
    toDateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        val fromDate: Date? = entryFilter.fromDate
        val toDate: Date? = entryFilter.toDate
        Text(
            text = fromDate?.let { DateUtils.displayDateFormat.format(it) } ?: "<<From>>",
            modifier = Modifier.clickable {
                fromDateClick()
            },
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )

        Text(text = " to ")

        Text(
            text = toDate?.let { DateUtils.displayDateFormat.format(it) } ?: "<<To>>",
            modifier = Modifier.clickable {
                toDateClick()
            },
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewDateView() {
    DateView(entryFilter = EntryFilter.forPreview(),{},{})
}
