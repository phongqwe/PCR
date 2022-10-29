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
    val fromDate:Date,
    val toDate:Date,
    val text:String,
    val tags:Collection<Tag>,
){
    private val datePeriod = fromDate.time .. toDate.time
    fun match(entry: Entry):Boolean{
        val dateMatch = entry.dateTime.time in datePeriod
        val textMatch = entry.detail?.contains(text) ?: false
        val containAllTag=entry.tags.containsAll(tags)
        return dateMatch && textMatch && containAllTag
    }
    companion object {
        fun forPreview():EntryFilter{
            return EntryFilter(
                fromDate = DateUtils.createDate(2000,1,1),
                toDate = DateUtils.createDate(2023,1,1),
                text ="",
                tags =  emptyList()
            )
        }
    }

    val dateDisplayText:AnnotatedString get(){
        val rt = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)){
                append(DateUtils.displayDateFormat.format(fromDate))
            }
            append(" to ")
            withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)){
                append(DateUtils.displayDateFormat.format(toDate))
            }
        }
        return rt
    }


}
