package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Entry

interface EntryContainer : Map<String, Entry> {
    val allEntries: List<Entry>

    fun loadFromDbAndOverwrite(): EntryContainer
    suspend fun susLoadFromDbAndOverWrite(): EntryContainer

    /**
     * Update the content of all entries in this container so that the db reflects them. This includes update the Tag table and TagAssignment table.
     */
    fun writeToDb():Rs<Unit,ErrorReport>
    suspend fun susWriteToDb():Rs<Unit,ErrorReport>
}
