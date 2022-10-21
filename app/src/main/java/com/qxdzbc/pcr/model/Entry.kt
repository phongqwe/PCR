package com.qxdzbc.pcr.model

import android.text.style.TtsSpan.DateBuilder
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.qxdzbc.pcr.util.DateUtils.parseDate
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Entity
data class Entry(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val money: Double,
    val detail:String,
    val dateTime:Long,
) {
    @Ignore
    val dateTimeObj:Date = Date(dateTime)
}
