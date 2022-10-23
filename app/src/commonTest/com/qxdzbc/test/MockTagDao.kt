package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.database.model.Tag
import com.qxdzbc.pcr.database.model.TagWithEntries

class MockTagDao(
    val tags: List<Tag> = emptyList(),
    val tweList: List<TagWithEntries> = emptyList(),
) : TagDao {
    override fun getTagWithEntries(): List<TagWithEntries> {
        return tweList
    }

    override fun getAll(): List<Tag> {
        return tags
    }

    @Throws(Exception::class)
    override fun insert(vararg tags: Tag) {

    }

    override fun delete(tag: Tag) {
    }
}
