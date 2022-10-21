package com.qxdzbc.pcr.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["entryId","tagId"])
data class TagAssignment(
    val entryId:Long,
    val tagId:Long,
){
//    companion object{
//        val tagId = "tagId"
//        val entryId = "entryId"
//    }
}
