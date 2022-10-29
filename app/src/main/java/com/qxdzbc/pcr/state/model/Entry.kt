package com.qxdzbc.pcr.state.model

import com.google.firebase.firestore.CollectionReference
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbTagAssignment
import com.qxdzbc.pcr.firestore.EntryDoc
import java.text.SimpleDateFormat
import java.util.*

interface Entry {

    val id: String
    val money: Double
    val detail: String?
    val dateTime: Date
    val tags: List<Tag>
    val isUploaded: Boolean
    val isCost:Boolean

    /**
     * Edit this entry, automatically mark the output as "not uploaded"
     */
    fun edit(
        money:Double = this.money,
        detail:String? = this.detail,
        dateTime:Date = this.dateTime,
        tags:List<Tag> = this.tags,
        isCost:Boolean = this.isCost
    ):Entry

    fun setIsUploaded(i: Boolean): Entry

    fun toDbEntry(): DbEntry
    fun toDbTagAssignments(): List<DbTagAssignment>

    fun toEntryDoc(tagColRef:CollectionReference): EntryDoc {
        return EntryDoc(
            id = id,
            detail = detail,
            money = money,
            date = dateTime.time,
            tagRefs = this.tags.map {
                tagColRef.document(it.tagId)
            }
        )
    }

    val displayDate:String
    val displayMoney:String
}
