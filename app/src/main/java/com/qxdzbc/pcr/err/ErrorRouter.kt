package com.qxdzbc.pcr.err

import com.qxdzbc.pcr.common.Rs

interface ErrorRouter {
    fun reportToFrontScreen(err:ErrorReport)
    fun <T> reportToFrontScreenIfNeed(rs:Rs<T,ErrorReport>):Rs<T,ErrorReport>
    fun reportToMainScreen(err: ErrorReport)
    fun <T> reportToMainScreenIfNeed(rs:Rs<T,ErrorReport>):Rs<T,ErrorReport>

}
