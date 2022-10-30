package com.qxdzbc.pcr.state.container.filter

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.util.DateUtils
import java.util.Date

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
        return fromDate != null && toDate != null
    }

    private val datePeriod = if (canBeUsed()) fromDate!!.time..toDate!!.time else null
    fun match(entry: Entry): Boolean {
        if (datePeriod != null) {
            val dateMatch = entry.dateTime.time in datePeriod
            val textMatch = text?.let { entry.detail?.contains(it) } ?: true
            val containAllTag = if (tags.isEmpty()) true else entry.tags.containsAll(tags)
            return dateMatch && textMatch && containAllTag
        } else {
            return true
        }
    }

    companion object {
        val empty = EntryFilter(null, null, null, emptyList())
        fun forPreview(): EntryFilter {
            return EntryFilter(
                fromDate = DateUtils.createDate(2000, 1, 1),
                toDate = DateUtils.createDate(2023, 1, 1),
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
