package com.qxdzbc.pcr.state

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.test.MockEntryDao
import com.qxdzbc.test.TestSample
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever

class EntryContainerImpTest {
    lateinit var ts: TestSample
    lateinit var cont:EntryContainerImp
    lateinit var entryDao:MockEntryDao
    @Before
    fun bf(){
        ts = TestSample()
        entryDao = MockEntryDao(
            entries = ts.entries
        )
        cont = EntryContainerImp(ts.entries.associateBy { it.id },entryDao)
    }

    @Test
    fun loadFromDb(){
        val c0=EntryContainerImp.empty(entryDao)
        assertTrue(c0.isEmpty())
        val c1 = c0.loadFromDbAndOverwrite()
        assertEquals(entryDao.entries, c1.allEntries)
    }

    @Test
    fun writeToDb(){
        val spyEntryDao = spy(MockEntryDao())
        // success call
        val c1 = cont.copy(entryDao = spyEntryDao)
       assertTrue(c1.writeToDb() is Ok)
        runBlocking {
            assertTrue(c1.susWriteToDb() is Ok)
        }
        // fail call
        whenever(spyEntryDao.insert(any<Entry>())) doThrow Exception()
        assertTrue(c1.writeToDb() is Err)
        runBlocking {
            assertTrue(c1.susWriteToDb() is Err)
        }
    }
}
