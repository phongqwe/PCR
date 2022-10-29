package com.qxdzbc.pcr.action.remove_err

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorReport

abstract class AbsRemoveErr constructor(
    val ecMs: Ms<ErrorContainer>
) : RemoveErrAction {
    override fun removeErr(err: ErrorReport) {
        ecMs.value = ecMs.value.remove(err)
    }
}
