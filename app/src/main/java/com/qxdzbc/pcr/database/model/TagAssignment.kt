package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["entryId","tagId"],
foreignKeys = [
    ForeignKey(
        entity = Entry::class,
        parentColumns = [Entry.idCol],
        childColumns = [TagAssignment.entryIdCol],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    ),
    ForeignKey(
        entity = Tag::class,
        parentColumns = [Tag.idCol],
        childColumns = [TagAssignment.tagIdCol],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )
])
data class TagAssignment(
    @ColumnInfo(name = entryIdCol)
    val entryId:String,
    @ColumnInfo(name = tagIdCol)
    val tagId:String,
){
    companion object{
        const val tagIdCol = "tagId"
        const val entryIdCol = "entryId"
    }
}
