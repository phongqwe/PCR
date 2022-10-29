package com.qxdzbc.pcr.action.remove_err

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.ErrorContInCreateEntryScreenMs
import com.qxdzbc.pcr.err.ErrorContainer
import javax.inject.Inject

class RemoveErrFromCreateEntryScreen @Inject constructor(
    @ErrorContInCreateEntryScreenMs
    ecMs: Ms<ErrorContainer>
) : AbsRemoveErr(ecMs)
