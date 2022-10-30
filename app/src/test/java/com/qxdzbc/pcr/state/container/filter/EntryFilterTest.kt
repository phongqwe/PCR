package com.qxdzbc.pcr.state.container.filter

import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.database.model.DbTag
import org.junit.Assert.*

import org.junit.Test
import java.util.*

class EntryFilterTest {
    @Test
    fun match() {
        val tags = listOf(
            DbTag("t1", "t1 name"),
            DbTag("t2", "t2 name"),
        )

        val filter = EntryFilter(
            fromDate = Date(1),
            toDate = Date(100),
            text = "a",
            tags = tags
        )

        val e = DbEntry.random().copy(
            detail ="abc", dateTime = Date(22).time
        )
        val ewt = DbEntryWithTags(
            entry = e,
            tags = tags + DbTag("t3", "t3 name")
        )
        assertTrue(filter.match(ewt))

        assertFalse(filter.match(ewt.copy(tags= tags - tags[0])))
        assertFalse(filter.match(ewt.copy(
            entry = e.copy(detail="q")
        )))
        assertFalse(filter.match(ewt.copy(
            entry = e.copy(dateTime = 1000)
        )))

        val filterSkipText = filter.clearTextFilter()
        assertTrue(filterSkipText.match(ewt.copy(entry=e.copy(detail ="123"))))
    }
}
