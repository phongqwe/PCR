package com.qxdzbc.pcr.state

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.state.container.EntryContainerImp
import com.qxdzbc.test.MockEntryDao
import com.qxdzbc.test.MockTagAssignmentDao
import com.qxdzbc.test.MockTagDao
import com.qxdzbc.test.TestSample
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class EntryContainerImpTest {
    lateinit var ts: TestSample
    lateinit var cont: EntryContainerImp
    lateinit var entryDao:MockEntryDao
    lateinit var tagDao:MockTagDao
    lateinit var tagAssignmentDao: MockTagAssignmentDao
    @Before
    fun bf(){
        ts = TestSample()
        entryDao = MockEntryDao(
            entries = ts.entries,
            entriesWithTags = ts.entriesWithTag
        )
         tagDao = MockTagDao(
            tags=ts.tags
        )
         tagAssignmentDao = MockTagAssignmentDao()
        cont = EntryContainerImp(ts.entriesWithTag.associateBy { it.id },entryDao,tagDao,tagAssignmentDao)
    }

    @Test
    fun loadFromDb(){
        val c0= EntryContainerImp.empty(entryDao,tagDao,tagAssignmentDao)
        assertTrue(c0.isEmpty())
        val c1 = c0.loadFromDbAndOverwrite()
        assertEquals(entryDao.entriesWithTags, c1.allEntries)
    }

    @Test
    fun writeToDb(){

        // success call
        val c1 = cont
       assertTrue(c1.writeToDb() is Ok)
        runBlocking {
            assertTrue(c1.susWriteToDb() is Ok)
        }
        // fail call
        val spyEntryDao = mock<EntryDao>{
            whenever(it.insertOrUpdate(any())) doAnswer {
                throw Exception()
            }
        }
        val c2 = cont.copy(entryDao = spyEntryDao)

        assertTrue(c2.writeToDb() is Err)
        runBlocking {
            assertTrue(c2.susWriteToDb() is Err)
        }
    }
}
