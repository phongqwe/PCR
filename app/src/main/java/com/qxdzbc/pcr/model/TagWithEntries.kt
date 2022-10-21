package com.qxdzbc.pcr.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TagWithEntries(
    @Embedded
    val tag:Tag,
    @Relation(
        parentColumn = "id", // tag id
        entityColumn = "id", // entry id
        associateBy = Junction(TagAssignment::class,
            parentColumn = "tagId",
            entityColumn = "entryId"
            )
    )
    val entries:List<Entry>
) {
}
