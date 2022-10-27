package com.qxdzbc.pcr.firestore

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag

interface FirebaseHelper {
    suspend fun writeTag(userId: String, tag: TagDoc): Rs<Unit, ErrorReport>
    suspend fun writeTag(userId: String,tag:Tag): Rs<Unit, ErrorReport>

    suspend fun writeMultiTags(userId: String,tags:List<Tag>):Rs<Unit, ErrorReport>

    suspend fun removeTag(userId: String,tag: TagDoc):Rs<Unit,ErrorReport>
    suspend fun removeTag(userId: String,tag: Tag):Rs<Unit,ErrorReport>
    suspend fun removeTag(userId: String,tagId:String):Rs<Unit,ErrorReport>

    suspend fun readAllTags(userId: String):Rs<List<TagDoc>,ErrorReport>
    suspend fun readAllTagsToModel(userId: String):Rs<List<Tag>,ErrorReport>

    suspend fun readMultiTagsById(userId: String, tagIds: Collection<String>): Rs<List<TagDoc>, ErrorReport>

    suspend fun writeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport>
    suspend fun writeEntry(userId: String, entryDoc: Entry): Rs<Unit, ErrorReport>

    suspend fun writeMultiEntries(userId: String, entries:List<Entry>):Rs<Unit, ErrorReport>

    suspend fun removeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport>
    suspend fun removeEntry(userId: String, entry: Entry): Rs<Unit, ErrorReport>
    suspend fun removeEntry(userId: String, entryId: String): Rs<Unit, ErrorReport>

    suspend fun readAllEntries(userId: String):Rs<List<EntryDoc>,ErrorReport>
    suspend fun readAllEntriesToModel(userId: String):Rs<List<Entry>,ErrorReport>
}
