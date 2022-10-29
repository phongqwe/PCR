package com.qxdzbc.pcr.state

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.err.OtherErrors
import com.qxdzbc.pcr.firestore.FirebaseHelper
import com.qxdzbc.pcr.firestore.MockFirebaseHelper
import com.qxdzbc.pcr.state.container.EntryContainerImp
import com.qxdzbc.test.MockEntryDao
import com.qxdzbc.test.MockTagAssignmentDao
import com.qxdzbc.test.MockTagDao
import com.qxdzbc.test.TestSample
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class EntryContainerImpTest {
    lateinit var ts: TestSample
    lateinit var cont: EntryContainerImp
    lateinit var entryDao: MockEntryDao
    lateinit var tagDao: MockTagDao
    lateinit var tagAssignmentDao: MockTagAssignmentDao
    lateinit var firebaseHelper: MockFirebaseHelper

    @Before
    fun bf() {
        ts = TestSample()
        entryDao = MockEntryDao(
            entries = ts.entries,
            entriesWithTags = ts.entriesWithTag
        )
        tagDao = MockTagDao(
            tags = ts.tags
        )
        firebaseHelper =
            MockFirebaseHelper(ts.entriesWithTag.subList(0, ts.entriesWithTag.size / 2))
        tagAssignmentDao = MockTagAssignmentDao()
        cont = EntryContainerImp(
            m = ts.entriesWithTag.associateBy { it.id },
            entryDao = entryDao,
            tagDao = tagDao,
            tagAssignmentDao = tagAssignmentDao,
            firestoreHelper = firebaseHelper
        )
    }

    @Test
    fun addEntryAndWriteToDb() {
        runBlocking {
            suspend fun okCase(){
                val e = DbEntryWithTags.random()
                assertTrue(e !in cont.allEntries)
                val rs=cont.addEntryAndWriteToDb(e)
                assertTrue(rs is Ok)
                assertTrue(e in rs.component1()!!.allEntries)
            }
            suspend fun unableToWriteToDb(){
                val e = DbEntryWithTags.random()
                val mockEntryDao = mock<EntryDao>(){
                    whenever(it.insert(e.toDbEntry())).thenAnswer {
                        throw Exception()
                    }
                }
                val c2 = cont.copy(entryDao = mockEntryDao)
                val rs = c2.addEntryAndWriteToDb(e)
                assertTrue(rs is Err)
            }

            okCase()
            unableToWriteToDb()
        }
    }

    @Test
    fun loadFromDb() {
        val c0 = EntryContainerImp.empty(entryDao, tagDao, tagAssignmentDao, firebaseHelper)
        assertTrue(c0.isEmpty())
        val c1 = c0.loadFromDbAndOverwrite()
        assertEquals(entryDao.entriesWithTags, c1.allEntries)
    }

    @Test
    fun writeToDb() {

        // success call
        val c1 = cont
        assertTrue(c1.writeToDb() is Ok)
        runBlocking {
            assertTrue(c1.susWriteToDb() is Ok)
        }
        // fail call
        val spyEntryDao = mock<EntryDao> {
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

    @Test
    fun loadFromFirebaseAndOverwrite() {
        runBlocking {
            val expect = firebaseHelper.readAllEntriesToModel("asd").component1()!!
            assertNotEquals(expect, cont.allEntries)
            val c2Rs = cont.loadFromFirestoreAndOverwrite("userId123")
            assertTrue(c2Rs is Ok)
            assertEquals(expect, c2Rs.component1()?.allEntries)
        }
    }

    @Test
    fun initLoad() {
        val uid = "userId"
        fun `load ok from db, dont load from firestore`() {
            val expect = entryDao.getEntryWithTag()
            val c2 = cont.clearAll()
            assertTrue(c2.allEntries.isEmpty())
            assertNotEquals(expect, c2.allEntries)
            runBlocking {
                val c3 = c2.initLoad(uid)
                assertEquals(expect, c3.allEntries)
            }
        }


        fun `load fail from db, attemp to load from firestore`() {
            val emptyEntryDao = MockEntryDao()
            val c2 = cont.copy(entryDao = emptyEntryDao).clearAll()
            runBlocking {
                val expect = firebaseHelper.readAllEntriesToModel(uid).component1()!!
                assertTrue(c2.allEntries.isEmpty())
                assertNotEquals(expect, c2.allEntries)
                val c3 = c2.initLoad(uid)
                assertEquals(expect, c3.allEntries)
            }
        }
        `load ok from db, dont load from firestore`()
        `load fail from db, attemp to load from firestore`()
    }
}
