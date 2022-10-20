package com.qxdzbc.pcr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "entry")
data class Entry(
    @PrimaryKey
    val uid:Int,
    @ColumnInfo(name="money")
    val money: Double,
    @ColumnInfo(name="detail")
    val detail:String,
    @ColumnInfo(name="date_time")
    val dateTime:LocalDateTime,
//    @ColumnInfo(name="tags")
//    val tags:Set<Tag>
) {
}
