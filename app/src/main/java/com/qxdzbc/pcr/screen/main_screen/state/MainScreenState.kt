package com.qxdzbc.pcr.screen.main_screen.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp

interface MainScreenState {
    val errorContainerSt:St<ErrorContainer>

    companion object{
        fun preview():MainScreenState{
            return MainScreenStateImp(ms(ErrorContainerImp()))
        }
    }
}
