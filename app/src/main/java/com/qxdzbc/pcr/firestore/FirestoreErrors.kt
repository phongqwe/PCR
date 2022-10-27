package com.qxdzbc.pcr.firestore

import com.qxdzbc.pcr.err.ErrorReport

object FirestoreErrors {
    const val prefix="FirestoreErrors"
    private val l = listOf(
        UnableToUpdateTag,UnableToUpdateEntry,
        UnableToDeleteTag,UnableToDeleteEntry,
        UnableToReadAllTag, UnableToReadAllEntry,
        UnableToWriteMultiTag,UnableToWriteMultiEntry,
        UnableToReadMultiTagById,UnableToReadMultiTagByRef
    )
    object UnableToReadMultiTagByRef{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "Unable to read multi tag ref from Firestore"
            )
        }
    }

    object UnableToReadMultiTagById{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "Unable to read multi tag by id from Firestore"
            )
        }
    }

    object UnableToWriteMultiEntry{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "Unable to write multi entries to Firestore"
            )
        }
    }

    object UnableToWriteMultiTag{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "Unable to write multi tags to Firestore"
            )
        }
    }

    object UnableToReadAllEntry{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "Unable to read all entry"
            )
        }
    }

    object UnableToReadAllTag{
        fun report(detail:String?=null): ErrorReport {
            return ErrorReport(
                code= "$prefix ${l.indexOf(this)}",
                detail = detail ?: "Unable to read all tag"
            )
        }
    }

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
