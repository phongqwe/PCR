package com.qxdzbc.pcr.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    /**
     * Parse a string date
     */
    @Throws(java.lang.IllegalArgumentException::class)
    fun parseDate(dateStr:String): Date {
        val format = SimpleDateFormat("yyyy-MM-ddTkk:mm:ss",Locale.US)
        val rt = format.parse(dateStr)
        return rt ?: throw IllegalArgumentException("invalid entry date format")
    }
}
