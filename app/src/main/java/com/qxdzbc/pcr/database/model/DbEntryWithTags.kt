package com.qxdzbc.pcr.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.qxdzbc.pcr.state.model.Entry
import java.util.*

data class DbEntryWithTags(
    @Embedded
    private val entry: DbEntry,
    @Relation(
        parentColumn = "id", // entry id
        entityColumn = "id", // tag id
        associateBy = Junction(
            DbTagAssignment::class,
            parentColumn = "entryId",
            entityColumn = "tagId")
    )
    override val tags:List<DbTag>
): Entry {
    override val id: String
        get() = entry.id
    override val money: Double
        get() = entry.money
    override val detail: String?
        get() = entry.detail
    override val dateTime: Date
        get() = Date(entry.dateTime)

    override fun toDbEntry(): DbEntry {
        return this.entry
    }

    override fun toDbTagAssignments(): List<DbTagAssignment> {
        return this.tags.map {
            DbTagAssignment(
                entryId = id,
                tagId=it.id
            )
        }
    }

}
