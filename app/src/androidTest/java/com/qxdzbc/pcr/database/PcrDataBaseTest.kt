package com.qxdzbc.pcr.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qxdzbc.pcr.common.TestSample
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.database.model.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PcrDataBaseTest {

    lateinit var db: AbsPcrDataBase
    lateinit var entryDao: EntryDao
    lateinit var tagDao: TagDao
    lateinit var tagAssDao: TagAssignmentDao
    lateinit var entries: List<Entry>
    lateinit var tagAsignments: List<TagAssignment>
    lateinit var tags: List<Tag>
    lateinit var ts: TestSample

    @Before
    fun b() {
        ts = TestSample()
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
        tagDao.insert(*tags.toTypedArray())
        entryDao.insert(*entries.toTypedArray())
        tagAssDao.insert(*tagAsignments.toTypedArray())
    }

    @Test
    fun getAll() {
        assertEquals(entries, entryDao.getAll())
        assertEquals(tags, tagDao.getAll())
        assertEquals(tagAsignments, tagAssDao.getAll())
    }

    @Test
    fun getEntryWithTags() {
        val q = entryDao.getEntryWithTag()
        assertEquals(
            entries.map { e ->
                EntryWithTags(
                    e,
                    tags.filter {
                        maxOf(e.id.toLong() / 2, 1).toString() == it.id
                    }
                )
            }, q
        )
    }

    @Test
    fun getTagWithEntries(){
        val q = tagDao.getTagWithEntries()
        val expect = tags.map { t->
            TagWithEntries(
                tag =  t,
                entries = entries.filter { e->
                    maxOf(e.id.toLong()/2,1).toString() == t.id
                }
            )
        }
        assertEquals(expect,q)
    }
}
