package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.WriteState
import com.qxdzbc.pcr.state.model.Tag
import java.util.*

interface EntryContainer : Map<String, Entry> {
    val allEntries: List<Entry>
    fun clearAll():EntryContainer
    fun loadFromDbAndOverwrite(): EntryContainer
    suspend fun susLoadFromDbAndOverWrite(): EntryContainer

    /**
     * Update the content of all entries in this container so that the db reflects them. This includes update the Tag table and TagAssignment table.
     */
    fun writeToDb():Rs<Unit,ErrorReport>
    suspend fun susWriteToDb():Rs<Unit,ErrorReport>

    /**
     * Load and overwrite this container with data from Firestore
     */
    suspend fun loadFromFirestoreAndOverwrite(userId:String):Rs<EntryContainer,ErrorReport>

    /**
     * Write the entire container to firestore.
     */
    suspend fun writeAllToFirestore(userId: String): Rs<EntryContainer, ErrorReport>

    /**
     * Only write un-uploaded entries to the firestore. Return a new entry container holding updated entries.
     */
    suspend fun writeUnUploadedToFirestore(userId: String): Rs<EntryContainer, ErrorReport>
    suspend fun writeUnUploadedToFirestore(): EntryContainer

    /**
     * First load from db. Only if data load from db is empty, then attempt to load from Firestore. If loading from Firestore cause error, silently discard the error, and return the container loaded from db.
     */
    suspend fun initLoad(userId: String?): EntryContainer

    fun filterEntries(filter: EntryFilter): List<Entry> {
        val avaliableEntries = allEntries.filter { it.writeState!=WriteState.DeletePending }
        if(filter.canBeUsed()){
            val rt= avaliableEntries.filter {
                val c2 = filter.match(it)
                c2
            }
            return rt
        }else{
            return avaliableEntries
        }
    }

    /**
     * Add an entry to the db and Firestore. Return a rs containing a new container
     */
    fun addEntryAndWriteToDb(newEntry: Entry): Rs<EntryContainer, ErrorReport>
    fun createEntryAndWriteToDb(
        date: Date,
        money:Double,
        detail:String?,
        tags:List<Tag>,
        isCost:Boolean
    ):Rs<EntryContainer, ErrorReport>

    fun addOrReplaceAndWriteToDb(entry:Entry): Rs<EntryContainer, ErrorReport>
    fun addOrReplaceAndWriteToDb(entries:List<Entry>): EntryContainer

    /**
     * Mark target entry as DeletePending, store them to db.
     * Then process to delete the target in this
     * order: firestore -> db -> memory.
     * Failing at any step will result in the container before delete and after marking.
     * Marked entries will no longer involve in any operation
     */
    suspend fun removeEntry(entry:Entry): EntryContainer
}

