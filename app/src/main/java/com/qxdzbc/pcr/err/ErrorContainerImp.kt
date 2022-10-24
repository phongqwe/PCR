package com.qxdzbc.pcr.err

data class ErrorContainerImp(
    private val l: List<ErrorReport> = emptyList()
) : ErrorContainer, List<ErrorReport> by l {
    override fun addErr(errorReport: ErrorReport): ErrorContainer {
        return this.copy(l = l + errorReport)
    }

    override fun remove(errorReport: ErrorReport): ErrorContainer {
        return this.copy(l = l - errorReport)
    }
}
