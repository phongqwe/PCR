package com.qxdzbc.pcr.state

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.err.ErrorReport

interface EntryContainer : Map<String, Entry> {
    val allEntries: Collection<Entry>

    fun loadFromDb(entryDao:EntryDao):EntryContainer
    suspend fun susLoadFromDb(entryDao:EntryDao):EntryContainer

    fun writeToDb(entryDao: EntryDao):Rs<Unit,ErrorReport>
    suspend fun susWriteToDb(entryDao: EntryDao):Rs<Unit,ErrorReport>
}

