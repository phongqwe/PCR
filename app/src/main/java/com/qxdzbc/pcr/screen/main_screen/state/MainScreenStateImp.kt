package com.qxdzbc.pcr.screen.main_screen.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.di.state.ErrorContInFrontMs
import com.qxdzbc.pcr.di.state.ErrorContInMainMs
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import javax.inject.Inject

class MainScreenStateImp @Inject constructor(
    @ErrorContInMainMs
    val errorContainerMs: Ms<ErrorContainer>
    ) :
    MainScreenState {
    override val errorContainerSt: St<ErrorContainer>
        get() = errorContainerMs


}
