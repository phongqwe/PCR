package com.qxdzbc.pcr.screen.main_screen.state

import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper

interface MainScreenState {
    val errorContainerSt:St<ErrorContainer>
    val userSt:St<FirebaseUserWrapper?>
    val isDark:Boolean

    companion object{
        val forPreview:MainScreenState get(){
            return MainScreenStateImp(
                errorContainerMs = ms(ErrorContainerImp()),
                isDarkSt = ms(true),
                userSt = ms(FirebaseUserWrapper.forPreview)
            )
        }
        const val mainScreenNavTag = "MainScreen_NavTag"
    }
}
