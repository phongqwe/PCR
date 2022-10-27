package com.qxdzbc.pcr.firestore

import com.qxdzbc.pcr.err.ErrorReport

object FirestoreErrors {
    const val prefix="FirestoreErrors"
    private val l = listOf(
        UnableToUpdateTag,UnableToUpdateEntry,
        UnableToDeleteTag,UnableToDeleteEntry
    )

    object UnableToUpdateTag{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "unable to update tag to firestore"
            )
        }
    }
    object UnableToDeleteTag{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "unable to delete tag in firestore"
            )
        }
    }

    object UnableToDeleteEntry{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "unable to delete entry in firestore"
            )
        }
    }

    object UnableToUpdateEntry{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "unable to update entry to firestore"
            )
        }
    }
}
