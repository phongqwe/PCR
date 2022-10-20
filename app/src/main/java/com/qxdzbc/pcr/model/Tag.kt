package com.qxdzbc.pcr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag")
data class Tag(
    @PrimaryKey
    val uid:Int,
    @ColumnInfo(name="name")
    val name:String
)
