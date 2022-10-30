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
    val displayDateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.US)
    fun randomDate():Date{
        return createDateAtStart((2000 .. 2022).random(),(0 .. 11).random(),(1 ..25).random())
    }

    fun createDateAtStart(year:Int, month:Int, day:Int):Date{
        return Calendar.getInstance().apply {
            set(year, month, day,0,0,0)
        }.time
    }

    fun createDateAtEnd(year:Int, month:Int, day:Int):Date{
        return Calendar.getInstance().apply {
            set(year, month, day,24,59,59)
        }.time
    }
    fun createDate(year:Int, month:Int, day:Int):Date{
        return Calendar.getInstance().apply {
            set(year, month, day)
        }.time
    }
}
