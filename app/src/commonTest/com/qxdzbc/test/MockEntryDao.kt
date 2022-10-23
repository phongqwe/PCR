package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags

class MockEntryDao(
    var entries:List<DbEntry> = emptyList(),
    var entriesWithTags: List<DbEntryWithTags> = emptyList()
) :EntryDao{
    override fun getEntryWithTag(): List<DbEntryWithTags> {
        return entriesWithTags
    }

    override fun getAll(): List<DbEntry> {
        return entries
    }
    @Throws(Exception::class)
    override fun insert(vararg entries: DbEntry) {
        // do nothing
    }

    override fun delete(entry: DbEntry) {
        // do nothing
    }
}
