package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = DbTagAssignment.tableName,
    primaryKeys = [DbTagAssignment.entryIdCol,DbTagAssignment.tagIdCol],
    foreignKeys = [
        ForeignKey(
            entity = DbEntry::class,
            parentColumns = [DbEntry.idCol],
            childColumns = [DbTagAssignment.entryIdCol],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = DbTag::class,
            parentColumns = [DbTag.idCol],
            childColumns = [DbTagAssignment.tagIdCol],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class DbTagAssignment(
    @ColumnInfo(name = entryIdCol)
    val entryId: String,
    @ColumnInfo(name = tagIdCol)
    val tagId: String,
) {
    companion object {
        const val tagIdCol = "tagId"
        const val entryIdCol = "entryId"
        const val tableName = "TagAssignment"
    }
}


//@JvmInline
//value class EntryId(private val i:String)
