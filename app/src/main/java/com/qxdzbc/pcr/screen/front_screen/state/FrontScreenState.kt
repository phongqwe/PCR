package com.qxdzbc.pcr.screen.front_screen.state

import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.err.ErrorContainer

interface FrontScreenState {
    val errorContainerSt:St<ErrorContainer>
    val isDark:Boolean
}
