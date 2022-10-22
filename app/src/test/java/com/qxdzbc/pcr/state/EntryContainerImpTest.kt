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
    @Before
    fun bf(){
        ts = TestSample()
        cont = EntryContainerImp(ts.entries.associateBy { it.id })
    }

    @Test
    fun loadFromDb(){
        val c0=EntryContainerImp.empty
        val entryDao = MockEntryDao(
            entries = ts.entries
        )
        assertTrue(c0.isEmpty())
        val c1 = c0.loadFromDb(entryDao)
        assertEquals(entryDao.entries, c1.allEntries)
    }

    @Test
    fun writeToDb(){
        val entryDao = spy(MockEntryDao())
        // success call
       assertTrue(cont.writeToDb(entryDao) is Ok)
        runBlocking {
            assertTrue(cont.susWriteToDb(entryDao) is Ok)
        }
        // fail call
        whenever(entryDao.insert(any<Entry>())) doThrow Exception()
        assertTrue(cont.writeToDb(entryDao) is Err)
        runBlocking {
            assertTrue(cont.susWriteToDb(entryDao) is Err)
        }
    }




}
