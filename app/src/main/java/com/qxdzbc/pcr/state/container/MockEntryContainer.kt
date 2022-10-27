package com.qxdzbc.pcr.state.container

import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.common.ResultUtils.toOk
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Entry

data class MockEntryContainer(val m: Map<String, Entry> = emptyMap()) : AbsEntryContainer(m) {
    override fun clearAll(): EntryContainer {
        return this.copy(m= emptyMap())
    }

    override fun loadFromDbAndOverwrite(): EntryContainer {
        return this
    }

    override suspend fun susLoadFromDbAndOverWrite(): EntryContainer {
        return this
    }

    override fun writeToDb(): Rs<Unit, ErrorReport> {
        return Ok(Unit)
    }

    override suspend fun susWriteToDb(): Rs<Unit, ErrorReport> {
        return Ok(Unit)
    }

    override suspend fun loadFromFirestoreAndOverwrite(userId: String): Rs<EntryContainer, ErrorReport> {
        return this.toOk()
    }

    override suspend fun writeToFirestore(userId: String): Rs<Unit, ErrorReport> {
        return Ok(Unit)
    }

    override suspend fun initLoad(userId: String?): EntryContainer {
        return this
    }
}
