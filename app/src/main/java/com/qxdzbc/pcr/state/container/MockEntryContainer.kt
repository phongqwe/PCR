package com.qxdzbc.pcr.state.container

import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.common.ResultUtils.toOk
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.database.model.DbTagWithEntries
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import java.util.*

data class MockEntryContainer(val m: Map<String, Entry> = emptyMap()) : AbsEntryContainer(m) {

    companion object{
        fun random():MockEntryContainer{
            return MockEntryContainer(
                m = (1 .. 20).map { DbEntryWithTags.random() }.associateBy { it.id }
            )
        }
    }
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

    override suspend fun writeAllToFirestore(userId: String): Rs<EntryContainer, ErrorReport> {
        return Ok(this)
    }

    override suspend fun writeUnUploadedToFirestore(userId: String): Rs<EntryContainer, ErrorReport> {
        return Ok(this)
    }

    override suspend fun initLoad(userId: String?): EntryContainer {
        return this
    }

    override fun addEntryAndWriteToDb(newEntry: Entry): Rs<EntryContainer, ErrorReport> {
        return Ok(this)
    }

    override fun createEntryAndWriteToDb(
        date: Date,
        money: Double,
        detail: String?,
        tags: List<Tag>,
        isCost: Boolean
    ): Rs<EntryContainer, ErrorReport> {
        return Ok(this)
    }

    override fun addOrReplaceAndWriteToDb(entry: Entry): Rs<EntryContainer, ErrorReport> {
        return Ok(this)
    }
}
