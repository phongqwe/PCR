package com.qxdzbc.pcr.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qxdzbc.pcr.database.AbsPcrDataBase
import com.qxdzbc.pcr.database.BasePcrDataBaseTest
import com.qxdzbc.test.TestSample
import com.qxdzbc.pcr.database.model.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TagDaoTest :BasePcrDataBaseTest() {
    @Test
    fun insertOrUpdate(){
        val oldAll = tagDao.getAll()
        val updates = listOf(
            DbTag("1","new Tag1"),
        )
        val inserts = listOf(
            DbTag("100","newTag"),
            DbTag("200","newTag")
        )

        assertTrue(oldAll.map { it.id }.containsAll(
            updates.map { it.id }
        ))
        assertFalse(oldAll.containsAll(updates))
        assertFalse(oldAll.containsAll(inserts))

        tagDao.insertOrUpdate(updates+inserts)

        val newAll = tagDao.getAll()
        assertTrue(newAll.containsAll(updates))
        assertTrue(newAll.containsAll(inserts))

    }

}
