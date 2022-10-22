package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.database.model.EntryWithTags

class MockEntryDao(
    var entries:List<Entry> = emptyList(),
    var entriesWithTags: List<EntryWithTags> = emptyList()
) :EntryDao{
    override fun getEntryWithTag(): List<EntryWithTags> {
        return entriesWithTags
    }

    override fun getAll(): List<Entry> {
        return entries
    }
    @Throws(Exception::class)
    override fun insert(vararg entries: Entry) {
        // do nothing
    }

    override fun delete(entry: Entry) {
        // do nothing
    }
}
