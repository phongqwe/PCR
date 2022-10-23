package com.qxdzbc.pcr.state.containe

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Entry

interface EntryContainer : Map<String, Entry> {
    val allEntries: List<Entry>

    fun loadFromDbAndOverwrite(): EntryContainer
    suspend fun susLoadFromDbAndOverWrite(): EntryContainer

    fun writeToDb():Rs<Unit,ErrorReport>
    suspend fun susWriteToDb():Rs<Unit,ErrorReport>
}

