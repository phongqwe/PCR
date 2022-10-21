package com.qxdzbc.pcr.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.model.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Long.max
import java.util.Calendar
import kotlin.math.exp
import kotlin.random.Random
import kotlin.random.nextInt

@RunWith(AndroidJUnit4::class)
class PcrDataBaseTest {

    lateinit var db: PcrDataBase
    lateinit var entryDao: EntryDao
    lateinit var tagDao: TagDao
    lateinit var tagAssDao: TagAssignmentDao
    lateinit var entries: List<Entry>
    lateinit var tagAsignments: List<TagAssignment>
    lateinit var tags: List<Tag>

    @Before
    fun b() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PcrDataBase::class.java).build()
        entryDao = db.entryDao()
        tagDao = db.tagDao()
        tagAssDao = db.tagAssignmentDao()
        createInitDataObj()
        insertTestData()
    }

    @After
    fun af() {
        db.close()
    }

    fun createInitDataObj() {
        entries = (1..10).map {
            Entry(
                id = it.toLong(),
                money = Random.nextInt(100..200).toDouble(),
                detail = "entry $it",
                dateTime = Calendar.Builder().setDate(1453, 5, it).build().timeInMillis
            )
        }
        tags = (1..5).map {
            Tag(id = it.toLong(), name = "Tag $it")
        }
        tagAsignments = (1..10).map {
            TagAssignment(
                entryId = it.toLong(),
                tagId = max(it.toLong() / 2, 1L)
            )
        }
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
                        max(e.id / 2, 1) == it.id
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
                    max(e.id/2,1) == t.id
                }
            )
        }
        assertEquals(expect,q)
    }
}
