package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import javax.inject.Inject

class MockEntryDao(
    var entries:List<DbEntry> ,
    var entriesWithTags: List<DbEntryWithTags>
) :EntryDao{
    @Inject
    constructor():this(emptyList(),emptyList())

    override fun getEntryWithTag(): List<DbEntryWithTags> {
        return entriesWithTags
    }

    override fun getAll(): List<DbEntry> {
        return entries
    }
    override fun insert(vararg entries: DbEntry) {
        // do nothing
    }

    override fun insert(entries: List<DbEntry>) {
    }

    override fun delete(entry: DbEntry) {
        // do nothing
    }

    override fun update(entries: List<DbEntry>) {
    }
}
