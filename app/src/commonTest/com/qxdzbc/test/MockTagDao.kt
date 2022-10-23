package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.database.model.DbTagWithEntries

open class MockTagDao(
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

    override fun insert(tags: List<DbTag>) {

    }

    override fun delete(tag: DbTag) {
    }

    override fun updateTag(tag: DbTag) {
    }

    override fun updateTags(tag: List<DbTag>) {

    }
    @Throws(Exception::class)
    open override fun insertOrUpdate(tags: List<DbTag>) {
        super.insertOrUpdate(tags)
    }
}
