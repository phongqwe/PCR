package com.qxdzbc.pcr.state

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.state.containe.TagContainerImp
import com.qxdzbc.test.MockTagDao
import com.qxdzbc.test.TestSample
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class TagContainerImpTest {
    lateinit var mockTagDao: MockTagDao
    lateinit var cont: TagContainerImp
    lateinit var ts: TestSample

    @Before
    fun bf() {
        ts = TestSample()
        mockTagDao = MockTagDao(ts.tags)
        cont = TagContainerImp(emptyMap(), mockTagDao)
    }

    @Test
    fun loadFromDb() {
        assertNotEquals(mockTagDao.getAll(), cont.allTags)
        val c1 = cont.loadFromDbAndOverwrite()
        assertEquals(mockTagDao.getAll(), c1.allTags)

        runBlocking {
            val c2 = cont.susLoadFromDbAndOverWrite()
            assertEquals(mockTagDao.getAll(), c2.allTags)
        }
    }

    @Test
    fun writeToDb() {

        val c1 = cont
        assertTrue(c1.writeToDb() is Ok)
        runBlocking {
            assertTrue(c1.susWriteToDb() is Ok)
        }
        val mockDao = mock<TagDao>() {
            whenever(it.insertOrUpdate(any())) doAnswer {
                throw Exception()
            }
        }
        val c2 = cont.copy(dao = mockDao)
        assertTrue(c2.writeToDb() is Err)
        runBlocking {
            assertTrue(c2.susWriteToDb() is Err)
        }
    }
}
