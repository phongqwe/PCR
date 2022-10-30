package com.qxdzbc.pcr.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.qxdzbc.pcr.database.BasePcrDataBaseTest
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.state.model.EntryState
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntryDaoTest : BasePcrDataBaseTest() {
    @Test
    fun insertOrUpdate(){
        val updates = listOf(
            DbEntry("1", money = -123.0,state= EntryState.WritePending.name),
        )

        val inserts = listOf(
            DbEntry("100",state= EntryState.WritePending.name),
            DbEntry("200",state= EntryState.WritePending.name),
        )

        val oldAll = entryDao.getAll()

        assertTrue(oldAll.map { it.id }.containsAll(updates.map { it.id }))
        assertFalse(oldAll.containsAll(inserts))
        entryDao.insertOrUpdate(inserts+updates)

        val newAll = entryDao.getAll()
        assertTrue(newAll.containsAll(inserts+updates))
    }
}
