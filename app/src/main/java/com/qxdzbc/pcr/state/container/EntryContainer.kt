package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import com.qxdzbc.pcr.state.model.Entry

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

    suspend fun writeToFirestore(userId: String):Rs<Unit,ErrorReport>

    /**
     * First load from db. Only if data load from db is empty, then attempt to load from Firestore. If loading from Firestore cause error, silently discard the error, and return the container loaded from db.
     */
    suspend fun initLoad(userId: String?): EntryContainer

    fun filterEntries(filter: EntryFilter?): List<Entry> {
        if(filter!=null){
            val rt= allEntries.filter {
                filter.match(it)
            }
            return rt
        }else{
            return allEntries
        }
    }
}

