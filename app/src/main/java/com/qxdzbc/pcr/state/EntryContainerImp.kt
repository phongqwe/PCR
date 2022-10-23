package com.qxdzbc.pcr.state

import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.common.ResultUtils.toOk
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.database.DbErrors
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.di.DefaultEntryMap
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.entry.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * keep a ref of dao make the functions neater to call, easier to look at, but make the container harder to create.
 * passing dao to function make that function only usable in place where an entry dao is available => more hassle than wiring the entry dao into the container
 */
data class EntryContainerImp @Inject constructor(
    @DefaultEntryMap
    private val m: Map<String, Entry>,
    private val entryDao:EntryDao,
) : EntryContainer, Map<String, Entry> by m {

    companion object {
        fun empty(entryDao: EntryDao) = EntryContainerImp(emptyMap(),entryDao)
    }

    override val allEntries: List<Entry> get() = m.values.toList()
    override fun loadFromDbAndOverwrite(): EntryContainer {
        val nm = entryDao.getEntryWithTag().associateBy { it.id }
        return this.copy(m = nm)
    }

    override suspend fun susLoadFromDbAndOverWrite(): EntryContainer {
        return withContext(Dispatchers.Default) {
            loadFromDbAndOverwrite()
        }
    }

    override fun writeToDb(): Rs<Unit, ErrorReport> {
        try {
            entryDao.insert()
            return Unit.toOk()
        } catch (e: Throwable) {
            val msg = "Unable to write entries in entry container into the db"
            return DbErrors.UnableToWriteEntryToDb.report(msg).toErr()
        }
    }

    override suspend fun susWriteToDb(): Rs<Unit, ErrorReport> {
        return withContext(Dispatchers.Default) {
            writeToDb()
        }
    }
}
