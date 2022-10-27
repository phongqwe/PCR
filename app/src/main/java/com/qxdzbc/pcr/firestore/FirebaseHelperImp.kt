package com.qxdzbc.pcr.firestore

import com.github.michaelbull.result.Ok
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.firestore.EntryDoc.Companion.entriesColPath
import com.qxdzbc.pcr.firestore.TagDoc.Companion.tagColPath
import com.qxdzbc.pcr.state.model.Entry
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseHelperImp @Inject constructor() : FirebaseHelper {
    val db = FirebaseFirestore.getInstance()
    val dbRoot = "users"
    override suspend fun writeTag(userId: String, tag: TagDoc): Rs<Unit, ErrorReport> {
        val task = tagDocRef(userId,tag.id).set(tag)
        task.await()
        if (task.isSuccessful) {
            return Ok(Unit)
        } else {
            return FirestoreErrors.UnableToUpdateTag.report().toErr()
        }
    }

    override suspend fun removeTag(userId: String, tag: TagDoc): Rs<Unit, ErrorReport> {
        val task = tagDocRef(userId,tag.id).delete()
        task.await()
        if (task.isSuccessful) {
            return Ok(Unit)
        } else {
            return FirestoreErrors.UnableToDeleteTag.report("Unable to delete tag ${tag.name}")
                .toErr()
        }
    }

    override suspend fun writeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport> {
        val task = entryDocRef(userId,entryDoc.id).set(entryDoc)
        task.await()
        if (task.isSuccessful) {
            return Ok(Unit)
        } else {
            return FirestoreErrors.UnableToUpdateEntry.report().toErr()
        }
    }

    override suspend fun writeEntry(userId: String, entryDoc: Entry): Rs<Unit, ErrorReport> {
        val tagColRef = tagColRef(userId)
        val e = entryDoc.toEntryDoc(tagColRef)
        return this.writeEntry(userId,e)
    }

    override suspend fun removeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport> {
        val task = entryDocRef(userId,entryDoc.id).delete()
        task.await()
        if (task.isSuccessful) {
            return Ok(Unit)
        } else {
            return FirestoreErrors.UnableToDeleteTag.report("Unable to delete entry ${entryDoc.id}")
                .toErr()
        }
    }
    private fun userDocRef(userId: String): DocumentReference {
        return db.collection(dbRoot).document(userId)
    }
    private fun entryDocRef(userId: String,entryId:String):DocumentReference{
        return entryColRef(userId).document(entryId)
    }

    private fun tagDocRef(userId:String, tagId:String):DocumentReference{
        return tagColRef(userId).document(tagId)
    }

    private fun entryColRef(userId:String):CollectionReference{
        return userDocRef(userId).collection(entriesColPath)
    }
    private fun tagColRef(userId: String):CollectionReference{
        return userDocRef(userId).collection(tagColPath)
    }
}
