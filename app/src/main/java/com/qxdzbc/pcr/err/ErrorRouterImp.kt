package com.qxdzbc.pcr.err

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.ErrorContInFrontMs
import javax.inject.Inject

class ErrorRouterImp @Inject constructor(
    @ErrorContInFrontMs
    private val errContMs:Ms<ErrorContainer>
) : ErrorRouter {
    override fun reportToFrontScreen(err: ErrorReport) {
        errContMs.value = errContMs.value.addErr(err)
    }

    override fun reportToMainScreen(err: ErrorReport) {
        TODO("Not yet implemented")
    }
}
