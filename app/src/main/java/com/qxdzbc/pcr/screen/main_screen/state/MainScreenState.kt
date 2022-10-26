package com.qxdzbc.pcr.screen.main_screen.state

import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp

interface MainScreenState {
    val errorContainerSt:St<ErrorContainer>
    val isDark:Boolean

    companion object{
        fun forPreview():MainScreenState{
            return MainScreenStateImp(
                errorContainerMs = ms(ErrorContainerImp()),
                isDarkSt = ms(true)
            )
        }
    }
}
