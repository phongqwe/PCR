package com.qxdzbc.pcr.database

import com.qxdzbc.pcr.err.ErrorReport

object DbErrors {
    val prefix = "DbError"
    private val l = listOf(
        UnableToWriteEntryToDb,UnableToWriteTagToDb
    )

    object UnableToWriteEntryToDb{
        fun report(detail:String?=null):ErrorReport{
            return ErrorReport(
                code= "${prefix} ${l.indexOf(this)}",
                detail = detail ?: "unable to write entries to db"
            )
        }
    }
    object UnableToWriteTagToDb{
        fun report(detail:String?=null):ErrorReport{
            return ErrorReport(
                code= "${prefix} ${l.indexOf(this)}",
                detail = detail ?: "unable to write tag to db"
            )
        }
    }
}
