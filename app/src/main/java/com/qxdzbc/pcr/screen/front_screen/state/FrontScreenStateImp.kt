package com.qxdzbc.pcr.screen.front_screen.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.di.state.ErrorContInFrontMs
import com.qxdzbc.pcr.di.state.IsDarkThemeSt
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.err.ErrorRouter
import javax.inject.Inject

class FrontScreenStateImp @Inject constructor(
    @ErrorContInFrontMs
    private val errorContainerMs: Ms<ErrorContainer> ,
    @IsDarkThemeSt
    private val isDarkSt:St<Boolean> ,
) : FrontScreenState {

    companion object{
        fun forTest():FrontScreenStateImp{
            return FrontScreenStateImp(
                errorContainerMs = ms(ErrorContainerImp()),
                isDarkSt = ms(true),
            )
        }
    }
    override val errorContainerSt: St<ErrorContainer>
        get() = errorContainerMs
    override val isDark: Boolean
        get() = isDarkSt.value
}
