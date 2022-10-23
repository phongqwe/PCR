package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =DbTag.tableName)
data class DbTag(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = idCol)
    val id:String,
    @ColumnInfo(name = nameCol)
    val name:String
){
    companion object{
        const val idCol="id"
        const val nameCol ="name"
        const val tableName="Tag"
    }
}
