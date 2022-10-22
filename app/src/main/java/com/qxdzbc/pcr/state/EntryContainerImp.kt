package com.qxdzbc.pcr.state

import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.common.ResultUtils.toOk
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.database.DbErrors
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.di.DefaultEntryMap
import com.qxdzbc.pcr.err.ErrorReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class EntryContainerImp @Inject constructor(
    @DefaultEntryMap
    private val m: Map<String, Entry>,
) : EntryContainer, Map<String, Entry> by m {

    companion object{
        val empty = EntryContainerImp(emptyMap())
    }

    override val allEntries: Collection<Entry> get() = m.values
    override fun loadFromDb(entryDao: EntryDao): EntryContainer {
        val nm = entryDao.getAll().associateBy { it.id }
        return this.copy(m = nm)
    }

    override suspend fun susLoadFromDb(entryDao: EntryDao): EntryContainer {
        return withContext(Dispatchers.Default){
            loadFromDb(entryDao)
        }
    }

    override fun writeToDb(entryDao: EntryDao): Rs<Unit, ErrorReport> {
        try{
            entryDao.insert()
            return Unit.toOk()
        }catch (e:Exception){
             return DbErrors.UnableToWriteEntryToDb.report().toErr()
        }
    }

    override suspend fun susWriteToDb(entryDao: EntryDao): Rs<Unit, ErrorReport> {
        return withContext(Dispatchers.Default){
            writeToDb(entryDao)
        }
    }
}
