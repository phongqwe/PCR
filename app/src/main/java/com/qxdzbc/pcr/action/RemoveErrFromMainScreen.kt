package com.qxdzbc.pcr.action

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.ErrorContInMainMs
import com.qxdzbc.pcr.err.ErrorContainer
import javax.inject.Inject

class RemoveErrFromMainScreen @Inject constructor(
    @ErrorContInMainMs
    ecMs: Ms<ErrorContainer>
) : AbsRemoveErr(ecMs)
