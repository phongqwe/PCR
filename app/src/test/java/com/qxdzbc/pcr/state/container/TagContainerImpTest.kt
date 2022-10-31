package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.BaseTest
import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.err.OtherErrors
import com.qxdzbc.pcr.firestore.FirestoreHelper
import com.qxdzbc.pcr.firestore.MockFirestoreHelper
import com.qxdzbc.pcr.state.model.WriteState
import com.qxdzbc.test.MockTagDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TagContainerImpTest : BaseTest() {

    @Test
    fun addTag() {
        val tagCont = TagContainerImp(
            m = emptyMap(),
            tagDao = MockTagDao(),
            firestoreHelper = MockFirestoreHelper()
        )
        val tag = DbTag.random()
        fun okCase() {
            assertTrue(tag !in tagCont.allValidTags)
            val cont2 = runBlocking {
                tagCont.addTagAndWriteToDb(tag)
            }
            assertTrue(tag.setWriteState(WriteState.WritePending) in cont2.allValidTags)
        }

        fun failAtDb() {
            val mockTagDao = mock<TagDao> {
                whenever(it.insertRs(tag)) doReturn OtherErrors.CommonErr.report().toErr()
            }
            val cont1 = tagCont.copy(
                tagDao = mockTagDao
            )
            assertTrue(tag !in cont1.allValidTags)
            val cont2 = runBlocking {
                cont1.addTagAndWriteToDb(tag)
            }
            assertEquals(cont1, cont2)
        }


        okCase()
        failAtDb()
//        failAtFirestore()
    }
    @Test
    fun uploadThePending() {
        val pendingTags = (1..5).map {
            DbTag.random().setWriteState(WriteState.WritePending)
        }
        val tagCont = TagContainerImp(
            m = pendingTags.associateBy { it.tagId },
            tagDao = MockTagDao(),
            firestoreHelper = MockFirestoreHelper()
        )

        fun okCase() {
            for(tag in tagCont.allValidTags){
                assertEquals(WriteState.WritePending,tag.writeState)
            }
            val c2 = runBlocking {
                tagCont.uploadThePendings()
            }
            for(tag in c2.allValidTags){
                assertEquals(WriteState.OK,tag.writeState)
            }
        }
        fun failAtFirestore(){
            val mockFirestore = mock<FirestoreHelper>{
                onBlocking {
                    it.writeMultiTags(pendingTags)
                } doReturn OtherErrors.CommonErr.report().toErr()
            }

            val cont1 = tagCont.copy(
                firestoreHelper = mockFirestore
            )
            for(tag in cont1.allValidTags){
                assertEquals(WriteState.WritePending,tag.writeState)
            }
            val cont2 = runBlocking {
                cont1.uploadThePendings()
            }
            assertEquals(cont2,cont1)
        }

        okCase()
        failAtFirestore()
    }
}
