package com.qxdzbc.pcr.firestore

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag

interface FirestoreHelper {
    suspend fun writeTag(userId: String, tag: TagDoc): Rs<Unit, ErrorReport>
    suspend fun writeTag(userId: String,tag:Tag): Rs<Unit, ErrorReport>
    suspend fun writeTag(tag:Tag): Rs<Unit, ErrorReport>

    suspend fun writeMultiTags(userId: String,tags:List<Tag>):Rs<Unit, ErrorReport>
    suspend fun writeMultiTags(tags:List<Tag>):Rs<Unit, ErrorReport>

    suspend fun removeTag(userId: String,tag: TagDoc):Rs<Unit,ErrorReport>
    suspend fun removeTag(userId: String,tag: Tag):Rs<Unit,ErrorReport>
    suspend fun removeTag(userId: String,tagId:String):Rs<Unit,ErrorReport>

    suspend fun readAllTags(userId: String):Rs<List<TagDoc>,ErrorReport>
    suspend fun readAllTagsToModel(userId: String):Rs<List<Tag>,ErrorReport>

    suspend fun readMultiTagsById(userId: String, tagIds: Collection<String>): Rs<List<TagDoc>, ErrorReport>

    suspend fun writeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport>

    /**
     * write an entry to firebase. Return a new entry that is similar to the input entry but marked as "uploaded"
     */
    suspend fun writeEntry(userId: String, entry: Entry): Rs<Entry, ErrorReport>

    /**
     * write multiple entries to firebase. Return a new entry list that is similar to the input entries but all marked as "uploaded"
     */
    suspend fun writeMultiEntries(userId: String, entries:List<Entry>): Rs<List<Entry>, ErrorReport>

    suspend fun removeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport>
    suspend fun removeEntry(userId: String, entry: Entry): Rs<Unit, ErrorReport>
    suspend fun removeEntry(userId: String, entryId: String): Rs<Unit, ErrorReport>

    suspend fun readAllEntries(userId: String):Rs<List<EntryDoc>,ErrorReport>
    suspend fun readAllEntriesToModel(userId: String):Rs<List<Entry>,ErrorReport>
}
