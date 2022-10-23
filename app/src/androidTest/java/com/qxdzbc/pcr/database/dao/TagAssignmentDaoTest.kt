package com.qxdzbc.pcr.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qxdzbc.pcr.database.BasePcrDataBaseTest
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbTagAssignment
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TagAssignmentDaoTest : BasePcrDataBaseTest() {

    @Test
    fun insertOrUpdate(){

        entryDao.insert(DbEntry("100"))
        entryDao.insert(DbEntry("200"))

        val oldAll = tagAssDao.getAll()
        val entryIdToBeUpdate = "1"
        val oldAssigAtEntry = oldAll.filter { it.entryId ==entryIdToBeUpdate }

        val updates = listOf(
            DbTagAssignment("1","5"),
            DbTagAssignment("1","9"),
        )

        val inserts = listOf<DbTagAssignment>(
            DbTagAssignment("100","9"),
            DbTagAssignment("200","9")
        )

        assertTrue(oldAll.map { it.entryId }.containsAll(updates.map { it.entryId }))
        assertFalse(oldAll.containsAll(inserts))

        tagAssDao.insertOrDelete(updates+inserts)
        val newAll = tagAssDao.getAll()
        assertTrue(newAll.containsAll(updates+inserts))
        assertFalse(newAll.containsAll(oldAssigAtEntry))
    }
}
