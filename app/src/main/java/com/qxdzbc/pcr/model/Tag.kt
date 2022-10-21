package com.qxdzbc.pcr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val name:String
)
