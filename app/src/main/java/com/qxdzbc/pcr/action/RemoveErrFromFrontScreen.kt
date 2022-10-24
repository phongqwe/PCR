package com.qxdzbc.pcr.action

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.ErrorContInFrontMs
import com.qxdzbc.pcr.err.ErrorContainer
import javax.inject.Inject

class RemoveErrFromFrontScreen @Inject constructor(
    @ErrorContInFrontMs
    ecMs:Ms<ErrorContainer>
) : AbsRemoveErr(ecMs)

