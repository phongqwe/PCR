package com.qxdzbc.pcr.screen.main_screen.state

import androidx.compose.runtime.getValue
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.di.state.ErrorContInFrontMs
import com.qxdzbc.pcr.di.state.ErrorContInMainMs
import com.qxdzbc.pcr.di.state.IsDarkThemeSt
import com.qxdzbc.pcr.di.state.UserSt
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import javax.inject.Inject

class MainScreenStateImp @Inject constructor(
    @ErrorContInMainMs
    val errorContainerMs: Ms<ErrorContainer>,
    @IsDarkThemeSt
    private val isDarkSt: St<Boolean>,
    @UserSt
    override val userSt: St<@JvmSuppressWildcards FirebaseUserWrapper?>,
) : MainScreenState {
    override val errorContainerSt: St<ErrorContainer>
        get() = errorContainerMs
    override val isDark: Boolean by isDarkSt
}
