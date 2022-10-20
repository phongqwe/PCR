package com.qxdzbc.pcr

import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Bench {
    @Test
    fun localDateTimeToIsoDate_in_sqlite() {
        val d = LocalDateTime.now()
        val dStr = d.format(DateTimeFormatter.ISO_DATE_TIME)
        val d2 = LocalDateTime.parse(dStr,DateTimeFormatter.ISO_DATE_TIME)
        assertEquals(d,d2)
    }
}
