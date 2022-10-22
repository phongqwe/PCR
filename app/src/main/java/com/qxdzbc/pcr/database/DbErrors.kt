package com.qxdzbc.pcr.database

import com.qxdzbc.pcr.err.ErrorReport

object DbErrors {
    val prefix = "DbError"
    object UnableToWriteEntryToDb{
        fun report(detail:String?=null):ErrorReport{
            return ErrorReport(
                code= "${prefix} 0",
                detail = detail ?: "unable to write entries to db"
            )
        }
    }
}
