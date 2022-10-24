package com.qxdzbc.pcr.err

interface ErrorContainer:List<ErrorReport> {
    fun addErr(errorReport: ErrorReport):ErrorContainer
    fun remove(errorReport: ErrorReport):ErrorContainer
}
