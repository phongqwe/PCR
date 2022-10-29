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

    override fun reportToFrontScreen(rs: Rs<Any, ErrorReport>) {
        rs.onFailure {
            this.reportToFrontScreen(it)
        }
    }

    override fun reportToMainScreen(err: ErrorReport) {
        errContInMainMs.value = errContInMainMs.value.addErr(err)
    }

    override fun reportToMainScreen(rs: Rs<Any, ErrorReport>) {
        rs.onFailure {
            this.reportToMainScreen(it)
        }
    }
}
