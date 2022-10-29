package com.qxdzbc.pcr.database.model

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.Relation
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.util.DateUtils
import java.text.SimpleDateFormat
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
            entityColumn = "tagId"
        )
    )
    override val tags: List<DbTag>
) : Entry {

    override val id: String get() = entry.id
    override val money: Double get() = entry.money
    override val detail: String? get() = entry.detail
    override val dateTime: Date get() = Date(entry.dateTime)
    override val isUploaded: Boolean
        get() = entry.isUploaded > 0
    override val isCost: Boolean
        get() = entry.isCost > 0

    override fun edit(
        money: Double,
        detail: String?,
        dateTime: Date,
        tags: List<Tag>,
        isCost: Boolean
    ): Entry {
        return this.copy(
            entry = entry.copy(
                money = money,
                detail =detail,
                dateTime = dateTime.time,
                isUploaded = 0,
                isCost = if(isCost) 1 else 0
            ),
            tags = tags.map { it.toDbTag() }
        )
    }

    override fun setIsUploaded(i: Boolean): Entry {
        return this.copy(
            entry = entry.setUploaded(i)
        )
    }

    override fun toDbEntry(): DbEntry {
        return this.entry
    }

    override fun toDbTagAssignments(): List<DbTagAssignment> {
        return this.tags.map { dbTag ->
            DbTagAssignment(
                entryId = id,
                tagId = dbTag.id
            )
        }
    }

    override val displayDate: String
        get() = DateUtils.displayDateFormat.format(this.dateTime)
    override val displayMoney: String
        get(){
            val rt = "$"+if(this.isCost){
                "-"
            }else{
                ""
            }+this.money.toString()
            return rt
        }

    companion object {
        fun random(): DbEntryWithTags {
            return DbEntryWithTags(
                entry = DbEntry.random(),
                tags = (1..4).map {
                    DbTag.random()
                }
            )
        }
    }
}
