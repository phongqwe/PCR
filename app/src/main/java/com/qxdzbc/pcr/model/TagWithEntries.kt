package com.qxdzbc.pcr.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TagWithEntries(
    @Embedded
    val tag:Tag,
    @Relation(
        parentColumn = Tag.idCol, // tag id
        entityColumn = Entry.idCol, // entry id
        associateBy = Junction(TagAssignment::class,
            parentColumn = "tagId",
            entityColumn = "entryId"
            )
    )
    val entries:List<Entry>
) {
}
