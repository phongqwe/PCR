package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.database.model.DbTagWithEntries

class MockTagDao(
    val tags: List<DbTag> = emptyList(),
    val tweList: List<DbTagWithEntries> = emptyList(),
) : TagDao {
    override fun getTagWithEntries(): List<DbTagWithEntries> {
        return tweList
    }

    override fun getAll(): List<DbTag> {
        return tags
    }

    @Throws(Exception::class)
    override fun insert(vararg tags: DbTag) {

    }

    override fun delete(tag: DbTag) {
    }
}
