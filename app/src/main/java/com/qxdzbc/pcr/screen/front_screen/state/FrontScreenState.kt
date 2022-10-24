package com.qxdzbc.pcr.screen.front_screen.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.err.ErrorContainer

interface FrontScreenState {
    val errorContainerSt:St<ErrorContainer>
    val isDark:Boolean
    companion object {
        const val frontScreenErrDialogTag = "frontScreenErrDialogTag"
        const val frontScreenNavTag = "FrontScreen_NavTag"
    }
}
