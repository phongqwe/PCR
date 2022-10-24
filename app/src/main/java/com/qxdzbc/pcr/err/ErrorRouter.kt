package com.qxdzbc.pcr.err

interface ErrorRouter {
    fun reportToFrontScreen(err:ErrorReport)
    fun reportToMainScreen(err: ErrorReport)
}
