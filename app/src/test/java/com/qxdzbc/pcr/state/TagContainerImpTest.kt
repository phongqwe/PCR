package com.qxdzbc.pcr.state

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.firestore.MockFirebaseHelper
import com.qxdzbc.pcr.state.container.TagContainerImp
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
    lateinit var firebaseHelper: MockFirebaseHelper
    @Before
    fun bf() {
        ts = TestSample()
        firebaseHelper = MockFirebaseHelper(tags =ts.tags.subList(0,ts.tags.size/2))
        mockTagDao = MockTagDao(ts.tags)
        cont = TagContainerImp(emptyMap(), mockTagDao,firebaseHelper)
    }

    @Test
    fun loadFromFirestoreAndOverwrite(){
        runBlocking {
            val expect = firebaseHelper.readAllTagsToModel("").component1()!!
            assertNotEquals(cont.allTags,expect)
            val rs = cont.loadFromFirestoreAndOverwrite("")
            assertEquals(expect,rs.component1()!!.allTags)
        }
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
        val c2 = cont.copy(tagDao = mockDao)
        assertTrue(c2.writeToDb() is Err)
        runBlocking {
            assertTrue(c2.susWriteToDb() is Err)
        }
    }
}
