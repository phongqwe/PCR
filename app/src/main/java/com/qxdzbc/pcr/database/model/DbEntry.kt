package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = DbEntry.tableName)
data class DbEntry(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = idCol)
    val id:String,
    @ColumnInfo(name = moneyCol)
    val money: Double = 0.0,
    @ColumnInfo(name = detailCol)
    val detail:String? = null,
    @ColumnInfo(name = dateTimeCol)
    val dateTime:Long = 0,
) {
    companion object{
        const val idCol = "id"
        const val moneyCol ="money"
        const val detailCol ="detail"
        const val dateTimeCol = "dateTime"
        const val tableName="Entry"
    }
}
