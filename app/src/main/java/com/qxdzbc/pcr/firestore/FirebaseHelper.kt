package com.qxdzbc.pcr.firestore

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Entry

interface FirebaseHelper {
    suspend fun writeTag(userId: String, tag: TagDoc): Rs<Unit, ErrorReport>
    suspend fun removeTag(userId: String,tag: TagDoc):Rs<Unit,ErrorReport>
    suspend fun writeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport>
    suspend fun writeEntry(userId: String, entryDoc: Entry): Rs<Unit, ErrorReport>
    suspend fun removeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport>
}
