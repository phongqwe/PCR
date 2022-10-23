package com.qxdzbc.pcr.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.qxdzbc.pcr.database.model.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PcrDataBaseTest :BasePcrDataBaseTest(){

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
                DbEntryWithTags(
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
            DbTagWithEntries(
                tag =  t,
                entries = entries.filter { e->
                    maxOf(e.id.toLong()/2,1).toString() == t.id
                }
            )
        }
        assertEquals(expect,q)
    }
}
