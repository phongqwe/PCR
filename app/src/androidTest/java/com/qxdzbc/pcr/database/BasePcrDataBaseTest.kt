package com.qxdzbc.pcr.database

import CommonTestSample
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.database.model.*
import org.junit.After
import org.junit.Before

abstract class BasePcrDataBaseTest {

    lateinit var db: AbsPcrDataBase
    lateinit var entryDao: EntryDao
    lateinit var tagDao: TagDao
    lateinit var tagAssDao: TagAssignmentDao
    lateinit var entries: List<DbEntry>
    lateinit var tagAsignments: List<DbTagAssignment>
    lateinit var tags: List<DbTag>
    lateinit var ts: CommonTestSample

    @Before
    fun b() {
        ts = CommonTestSample()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AbsPcrDataBase::class.java).build()
        entryDao = db.entryDao
        tagDao = db.tagDao
        tagAssDao = db.tagAssignmentDao
        createInitDataObj()
        insertTestData()
    }

    @After
    fun af() {
        db.close()
    }

    fun createInitDataObj() {
        entries = ts.entries
        tags = ts.tags
        tagAsignments = ts.tagAsignments
    }

    fun insertTestData() {
        tagDao.insertVA(*tags.toTypedArray())
        entryDao.insert(*entries.toTypedArray())
        tagAssDao.insert(*tagAsignments.toTypedArray())
    }
}
