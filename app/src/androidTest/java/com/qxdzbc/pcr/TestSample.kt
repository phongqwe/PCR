package com.qxdzbc.pcr

import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.database.model.Tag
import com.qxdzbc.pcr.database.model.TagAssignment
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class TestSample {
    val entries = (1..10).map {
        Entry(
            id = it.toLong(),
            money = Random.nextInt(100..200).toDouble(),
            detail = "entry $it",
            dateTime = Calendar.Builder().setDate(1453, 5, it).build().timeInMillis
        )
    }
    val tags = (1..5).map {
        Tag(id = it.toLong(), name = "Tag $it")
    }
    val tagAsignments = (1..10).map {
        TagAssignment(
            entryId = it.toLong(),
            tagId = maxOf(it.toLong() / 2, 1L)
        )
    }
}
