package com.qxdzbc.pcr.firestore

import androidx.compose.runtime.getValue
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapError
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.common.ResultUtils.toOk
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.di.state.HasNetworkConnectionMs
import com.qxdzbc.pcr.di.state.HasNetworkConnectionSt
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.firestore.EntryDoc.Companion.entriesColPath
import com.qxdzbc.pcr.firestore.TagDoc.Companion.tagColPath
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreHelperImp @Inject constructor(
    @HasNetworkConnectionSt
    val hasNetworkConnectionSt: St<@JvmSuppressWildcards Boolean>,
) : FirestoreHelper {

    val db = FirebaseFirestore.getInstance()
    val dbRoot = "users"

    override suspend fun writeTag(userId: String, tag: TagDoc): Rs<Unit, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = tagDocRef(userId, tag.id).set(tag)
            task.await()
            if (task.isSuccessful) {
                Ok(Unit)
            } else {
                FirestoreErrors.UnableToUpdateTag.report().toErr()
            }
        }
        return rt
    }

    override suspend fun writeTag(userId: String, tag: Tag): Rs<Unit, ErrorReport> {
        return this.writeTag(userId, tag.toTagDoc())
    }

    private val hasNetworkConnection by hasNetworkConnectionSt

    private suspend fun <T> runWhenHaveNetwork(f: suspend () -> Rs<T, ErrorReport>): Rs<T, ErrorReport> {
        if (hasNetworkConnection) {
            return f()
        } else {
            return FirestoreErrors.NoNetwork.report().toErr()
        }
    }

    override suspend fun writeMultiTags(userId: String, tags: List<Tag>): Rs<Unit, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val tagColRef = tagColRef(userId)
            val task = db.runBatch { batch ->
                for (tag in tags) {
                    batch.set(tagColRef.document(tag.tagId), tag.toTagDoc())
                }
            }
            task.await()
            if (task.isSuccessful) {
                Ok(Unit)
            } else {
                FirestoreErrors.UnableToWriteMultiTag.report().toErr()
            }
        }
        return rt
    }

    override suspend fun removeTag(userId: String, tag: TagDoc): Rs<Unit, ErrorReport> {
        val rs = this.removeTag(userId, tag.id)
        val rt = rs.mapError {
            FirestoreErrors.UnableToDeleteTag.report("Unable to delete tag ${tag.name}")
        }
        return rt
    }

    override suspend fun removeTag(userId: String, tag: Tag): Rs<Unit, ErrorReport> {
        return this.removeTag(userId, tag.tagId)
    }

    override suspend fun removeTag(userId: String, tagId: String): Rs<Unit, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = tagDocRef(userId, tagId).delete()
            task.await()
            if (task.isSuccessful) {
                Ok(Unit)
            } else {
                FirestoreErrors.UnableToDeleteTag.report("Unable to delete tag ${tagId}")
                    .toErr()
            }
        }
        return rt
    }

    override suspend fun readAllTags(userId: String): Rs<List<TagDoc>, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = tagColRef(userId).get()
            task.await()
            if (task.isSuccessful) {
                val query = task.result
                val rt =
                    query?.documents?.map { it.toObject<TagDoc>() }?.filterNotNull() ?: emptyList()
                Ok(rt)
            } else {
                FirestoreErrors.UnableToReadAllTag.report().toErr()
            }
        }
        return rt

    }

    override suspend fun readAllTagsToModel(userId: String): Rs<List<Tag>, ErrorReport> {
        val tagDocsRs = this.readAllTags(userId)
        val rt = tagDocsRs.map { tagDocs ->
            tagDocs.map { td ->
                DbTag.fromTagDoc(td)
            }
        }
        return rt
    }

    override suspend fun writeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = entryDocRef(userId, entryDoc.id).set(entryDoc)
            task.await()
            if (task.isSuccessful) {
                Ok(Unit)
            } else {
                FirestoreErrors.UnableToUpdateEntry.report().toErr()
            }
        }
        return rt
    }

    override suspend fun writeEntry(userId: String, entry: Entry): Rs<Entry, ErrorReport> {
        val tagColRef = tagColRef(userId)
        val e = entry.toEntryDoc(tagColRef)
        val rt = this.writeEntry(userId, e).map {
            entry.setIsUploaded(true)
        }
        return rt
    }

    override suspend fun writeMultiEntries(
        userId: String,
        entries: List<Entry>
    ): Rs<List<Entry>, ErrorReport> {
        val entryColRef = entryColRef(userId)
        val tagColRef = tagColRef(userId)
        val task = db.runBatch { b ->
            for (entry in entries) {
                b.set(entryColRef.document(entry.id), entry.toEntryDoc(tagColRef))
            }
        }
        task.await()
        if (task.isSuccessful) {
            return Ok(entries.map { it.setIsUploaded(true) })
        } else {
            return FirestoreErrors.UnableToWriteMultiEntry.report().toErr()
        }
    }

    override suspend fun removeEntry(userId: String, entryDoc: EntryDoc): Rs<Unit, ErrorReport> {
        return removeEntry(userId, entryDoc.id)
    }

    override suspend fun removeEntry(userId: String, entry: Entry): Rs<Unit, ErrorReport> {
        return removeEntry(userId, entry.id)
    }

    override suspend fun removeEntry(userId: String, entryId: String): Rs<Unit, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = entryDocRef(userId, entryId).delete()
            task.await()
            if (task.isSuccessful) {
                Ok(Unit)
            } else {
                FirestoreErrors.UnableToDeleteTag.report("Unable to delete entry ${entryId}")
                    .toErr()
            }
        }
        return rt
    }

    override suspend fun readAllEntries(userId: String): Rs<List<EntryDoc>, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = entryColRef(userId).get()
            task.await()
            if (task.isSuccessful) {
                val query = task.result
                val rt =
                    query?.documents?.map { it.toObject<EntryDoc>() }?.filterNotNull()
                        ?: emptyList()
                Ok(rt)
            } else {
                FirestoreErrors.UnableToReadAllEntry.report().toErr()
            }
        }
        return rt

    }

    override suspend fun readMultiTagsById(
        userId: String,
        tagIds: Collection<String>
    ): Rs<List<TagDoc>, ErrorReport> {
        val tagColRef = tagColRef(userId)
        val refs = tagIds.map { tagColRef.document(it) }
        val rs = readMultiTagByRef(refs)
        val rt = rs.mapError {
            FirestoreErrors.UnableToReadMultiTagById.report()
        }
        return rt
    }

    private suspend fun readMultiTagByRef(tagRefs: Collection<DocumentReference>): Rs<List<TagDoc>, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = db.runTransaction { trans ->
                val rs = mutableListOf<TagDoc>()
                for (ref in tagRefs) {
                    trans.get(ref).toObject<TagDoc>()?.also {
                        rs.add(it)
                    }
                }
                rs
            }
            task.await()
            if (task.isSuccessful) {
                task.result.toOk()
            } else {
                FirestoreErrors.UnableToReadMultiTagByRef.report().toErr()
            }
        }
        return rt
    }

    private suspend fun readMultiTagByRefToMap(tagRefs: Collection<DocumentReference>): Rs<Map<DocumentReference, TagDoc>, ErrorReport> {
        val rt = runWhenHaveNetwork {
            val task = db.runTransaction { trans ->
                val rs: MutableMap<DocumentReference, TagDoc> = mutableMapOf()
                for (ref in tagRefs) {
                    trans.get(ref).toObject<TagDoc>()?.also {
                        rs[ref] = it
                    }
                }
                rs
            }
            task.await()
            if (task.isSuccessful) {
                task.result.toOk()
            } else {
                FirestoreErrors.UnableToReadMultiTagByRef.report().toErr()
            }
        }
        return rt
    }

    override suspend fun readAllEntriesToModel(userId: String): Rs<List<Entry>, ErrorReport> {
        val allEntryDocsRs = this.readAllEntries(userId)
        val rt = allEntryDocsRs.flatMap { entryDocs ->
            val tagRefs = entryDocs.flatMap { it.tagRefs }.toSet()
            val tagMapRs = readMultiTagByRefToMap(tagRefs)
            tagMapRs.map { tagMap ->
                entryDocs.map { ed ->
                    DbEntryWithTags(
                        entry = DbEntry.fromEntryDoc(ed),
                        tags = ed.tagRefs.mapNotNull {
                            tagMap[it]
                        }.map { DbTag.fromTagDoc(it) }
                    )
                }
            }
        }
        return rt
    }


    private fun userDocRef(userId: String): DocumentReference {
        return db.collection(dbRoot).document(userId)
    }

    private fun entryDocRef(userId: String, entryId: String): DocumentReference {
        return entryColRef(userId).document(entryId)
    }

    private fun tagDocRef(userId: String, tagId: String): DocumentReference {
        return tagColRef(userId).document(tagId)
    }

    private fun entryColRef(userId: String): CollectionReference {
        return userDocRef(userId).collection(entriesColPath)
    }

    private fun tagColRef(userId: String): CollectionReference {
        return userDocRef(userId).collection(tagColPath)
    }
}
