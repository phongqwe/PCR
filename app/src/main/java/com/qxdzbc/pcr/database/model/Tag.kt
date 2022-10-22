package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = idCol)
    val id:Long=0,
    @ColumnInfo(name = nameCol)
    val name:String
){
    companion object{
        const val idCol="id"
        const val nameCol ="name"
    }
}
