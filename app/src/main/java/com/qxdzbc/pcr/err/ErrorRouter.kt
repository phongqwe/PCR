package com.qxdzbc.pcr.err

import com.qxdzbc.pcr.common.Rs

interface ErrorRouter {
    fun reportToFrontScreen(err:ErrorReport)
    fun reportToFrontScreen(rs:Rs<Any,ErrorReport>)
    fun reportToMainScreen(err: ErrorReport)
    fun reportToMainScreen(rs:Rs<Any,ErrorReport>)

}
