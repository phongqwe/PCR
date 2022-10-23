package com.qxdzbc.pcr.state

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.err.ErrorReport

interface EntryContainer : Map<String, Entry> {
    val allEntries: List<Entry>

    fun loadFromDbAndOverwrite():EntryContainer
    suspend fun susLoadFromDbAndOverWrite():EntryContainer

    fun writeToDb():Rs<Unit,ErrorReport>
    suspend fun susWriteToDb():Rs<Unit,ErrorReport>
}

