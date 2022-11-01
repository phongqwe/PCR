package com.qxdzbc.pcr.util

import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.firestore.FirestoreErrors
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper

object Utils {
    suspend fun <T> St<FirebaseUserWrapper?>.runWhenLoggedIn(f: suspend (userId: String) -> Rs<T, ErrorReport>): Rs<T, ErrorReport> {
        val rt = this.value?.uid?.let {
            f(it)
        } ?: FirestoreErrors.InvalidUser.report("not logged in").toErr()
        return rt
    }
}
