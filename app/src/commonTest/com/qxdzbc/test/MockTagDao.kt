package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.database.model.DbTagWithEntries
import javax.inject.Inject

open class MockTagDao(
    val tags: List<DbTag>,
    val tweList: List<DbTagWithEntries>,
) : TagDao {

    @Inject
    constructor():this(emptyList(),emptyList())

    override fun getTagWithEntries(): List<DbTagWithEntries> {
        return tweList
    }

    override fun getAll(): List<DbTag> {
        return tags
    }

    @Throws(Exception::class)
    override fun insertVA(vararg tags: DbTag) {

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
