package com.qxdzbc.pcr.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EntryWithTags(
    @Embedded
    val entry:Entry,
    @Relation(
        parentColumn = "id", // entry id
        entityColumn = "id", // tag id
        associateBy = Junction(TagAssignment::class,
            parentColumn = "entryId",
            entityColumn = "tagId")
    )
    val tags:List<Tag>
)
