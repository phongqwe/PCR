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
    @ColumnInfo(name = idCol)
    val id:Long = 0,
    @ColumnInfo(name = moneyCol)
    val money: Double,
    @ColumnInfo(name = detailCol)
    val detail:String,
    @ColumnInfo(name = dateTimeCol)
    val dateTime:Long,
) {
    @Ignore
    val dateTimeObj:Date = Date(dateTime)

    companion object{
        const val idCol = "id"
        const val moneyCol ="money"
        const val detailCol ="detail"
        const val dateTimeCol = "dateTime"
    }
}
