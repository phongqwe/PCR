package com.qxdzbc.pcr.action.create_entry

import androidx.compose.runtime.getValue
import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.BaseTest
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.WriteState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CreateEntryActionImpTest : BaseTest() {

    @Test
    fun addEntryAndWriteToDbAndAttemptFirebase() {
        val act = ts.comp.createEntryActionImp()
        val entryDao: EntryDao = ts.comp.mockEntryDao()
        val firestoreHelper = ts.comp.firestoreHelper()
        val newEntry: Entry = DbEntryWithTags.random()
        val ec by act.ecMs
        assertTrue(newEntry !in ec.allEntries)
        val rs = runBlocking {
            act.addEntryAndWriteToDbAndAttemptFirebase(newEntry)
        }
        assertTrue(rs is Ok)
        assertTrue(newEntry.setIsUploaded(true).setWriteState(WriteState.OK) in ec.allEntries)
        verify(entryDao, times(1)).insert(newEntry.toDbEntry())
        runBlocking {
            verify(firestoreHelper, times(1)).writeMultiEntries(ts.fakeUser.uid, listOf(newEntry))
        }
    }
}
