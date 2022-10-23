package com.qxdzbc.pcr.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class DbTagWithEntries(
    @Embedded
    val tag: DbTag,
    @Relation(
        parentColumn = DbTag.idCol, // tag id
        entityColumn = DbEntry.idCol, // entry id
        associateBy = Junction(
            DbTagAssignment::class,
            parentColumn = DbTagAssignment.tagIdCol,
            entityColumn = DbTagAssignment.entryIdCol,
        )
    )
    val entries: List<DbEntry>
) {
}
