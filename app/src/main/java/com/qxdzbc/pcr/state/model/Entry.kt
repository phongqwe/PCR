package com.qxdzbc.pcr.state.model

import com.google.firebase.firestore.CollectionReference
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbTagAssignment
import com.qxdzbc.pcr.firestore.EntryDoc
import java.util.*

interface Entry {

    val id: String
    val money: Double
    val detail: String?
    val dateTime: Date
    val tags: List<Tag>
    val isUploaded: Boolean
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
}
