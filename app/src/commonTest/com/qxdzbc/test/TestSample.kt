package com.qxdzbc.test

import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.database.model.Tag
import com.qxdzbc.pcr.database.model.TagAssignment
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class TestSample {
    val entries = (1..10).map {
        Entry(
            id = it.toString(),
            money = Random.nextInt(100..200).toDouble(),
            detail = "entry $it",
            dateTime = Date().time
        )
    }
    val tags = (1..5).map {
        Tag(id = it.toString(), name = "Tag $it")
    }
    val tagAsignments = (1..10).map {
        TagAssignment(
            entryId = it.toString(),
            tagId = maxOf(it.toLong() / 2, 1L).toString()
        )
    }
}
