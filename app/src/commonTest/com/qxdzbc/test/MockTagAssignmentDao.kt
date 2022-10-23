package com.qxdzbc.test

import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.model.DbTagAssignment

open class MockTagAssignmentDao(
    val tagAssignments: List<DbTagAssignment> = emptyList()
): TagAssignmentDao {
    override fun insert(vararg tagAssignment: DbTagAssignment) {
    }

    override fun insert(tagAssignments: List<DbTagAssignment>) {
    }

    override fun delete(vararg tagAssignment: DbTagAssignment) {
    }

    override fun delete(tagAssignment: List<DbTagAssignment>) {
    }

    override fun getAll(): List<DbTagAssignment> {
        return this.tagAssignments
    }

    override fun update(tagAssignments: List<DbTagAssignment>) {
    }

}
