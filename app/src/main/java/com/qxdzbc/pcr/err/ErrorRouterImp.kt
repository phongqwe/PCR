package com.qxdzbc.pcr.err

import com.github.michaelbull.result.onFailure
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.di.state.ErrorContInFrontMs
import com.qxdzbc.pcr.di.state.ErrorContInMainMs
import javax.inject.Inject

class ErrorRouterImp @Inject constructor(
    @ErrorContInFrontMs
    private val errContMs:Ms<ErrorContainer>,
    @ErrorContInMainMs
    private val errContInMainMs:Ms<ErrorContainer>
) : ErrorRouter {
    override fun reportToFrontScreen(err: ErrorReport) {
        errContMs.value = errContMs.value.addErr(err)
    }

    override fun <T> reportToFrontScreenIfNeed(rs: Rs<T, ErrorReport>): Rs<T, ErrorReport> {
        rs.onFailure {
            this.reportToFrontScreen(it)
        }
        return rs
    }

    override fun reportToMainScreen(err: ErrorReport) {
        errContInMainMs.value = errContInMainMs.value.addErr(err)
    }

    override fun <T> reportToMainScreenIfNeed(rs: Rs<T, ErrorReport>): Rs<T, ErrorReport> {
        rs.onFailure {
            this.reportToMainScreen(it)
        }
        return rs
    }
}
