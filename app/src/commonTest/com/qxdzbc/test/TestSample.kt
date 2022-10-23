package com.qxdzbc.test

import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.database.model.DbTagAssignment
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class TestSample {
    val entries = (1..10).map {
        DbEntry(
            id = it.toString(),
            money = Random.nextInt(100..200).toDouble(),
            detail = "entry $it",
            dateTime = Date().time
        )
    }
    val tags = (1..5).map {
        DbTag(id = it.toString(), name = "Tag $it")
    }
    val tagAsignments = (1..10).map {
        DbTagAssignment(
            entryId = it.toString(),
            tagId = maxOf(it.toLong() / 2, 1L).toString()
        )
    }
    val entriesWithTag:List<DbEntryWithTags> = run{
        val m=tagAsignments.groupBy { it.entryId }.map { (entryId,tagList)->
            DbEntryWithTags(
                entry = entries.first { it.id == entryId },
                tags = tagList.map { ta->
                    tags.first{it.id == ta.tagId}
                }
            )
        }
        m
    }
}
