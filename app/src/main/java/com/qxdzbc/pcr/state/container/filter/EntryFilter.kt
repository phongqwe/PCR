package com.qxdzbc.pcr.state.container.filter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.util.DateUtils
import java.util.*

data class EntryFilter(
    val fromDate: Date?,
    val toDate: Date?,
    val text: String?,
    val tags: Collection<Tag>,
) {

    fun clearDateFilter(): EntryFilter {
        return this.copy(fromDate = null, toDate = null)
    }

    fun clearTextFilter(): EntryFilter {
        return this.copy(text = null)
    }

    fun clearTagFilter(): EntryFilter {
        return this.copy(tags = emptyList())
    }

    fun clearAll(): EntryFilter {
        return empty
    }

    fun canBeUsed(): Boolean {
        val c1= fromDate != null && toDate != null
        val c2 = text!=null
        val c3 = tags.isNotEmpty()
        return c1 || c2 || c3
    }



    val datePeriod = if (fromDate != null && toDate != null) fromDate.time..toDate.time else null
    fun match(entry: Entry): Boolean {
        val dateMatch = if (datePeriod != null) {
             entry.dateTime.time in datePeriod
        } else {
             true
        }
        val textMatch = text?.let { entry.detail?.lowercase()?.contains(it.lowercase()) } ?: true
        val containAllTag = if (tags.isEmpty()) true else entry.tags.containsAll(tags)
        return dateMatch && textMatch && containAllTag
    }

    companion object {
        val empty = EntryFilter(null, null, null, emptyList())
        fun forPreview(): EntryFilter {
            return EntryFilter(
                fromDate = DateUtils.createDateAtStart(2000, 0, 1),
                toDate = DateUtils.createDateAtStart(2023, 0, 1),
                text = "",
                tags = emptyList()
            )
        }
    }

    val dateDisplayText: AnnotatedString
        get() {
            val rt = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    append(DateUtils.displayDateFormat.format(fromDate))
                }
                append(" to ")
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    append(DateUtils.displayDateFormat.format(toDate))
                }
            }
            return rt
        }


}
