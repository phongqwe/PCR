package com.qxdzbc.pcr.state.app

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.di.DefaultUserMs
import com.qxdzbc.pcr.di.state.FrontScreeStateMs
import com.qxdzbc.pcr.di.state.IsDarkThemeMs
import com.qxdzbc.pcr.di.state.MainScreenStateMs
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.screen.front_screen.state.FrontScreenState
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import javax.inject.Inject

data class AppStateImp @Inject constructor(
    @FrontScreeStateMs
    override val frontScreenStateMs:Ms<FrontScreenState>,
    @MainScreenStateMs
    override val mainScreenStateMs:Ms<MainScreenState>,
    override val errorReporter: ErrorRouter,
    @IsDarkThemeMs
    override val isDarkThemeMs: Ms<Boolean>,
    @DefaultUserMs
    override val userMs:Ms<FirebaseUserWrapper?> = ms(null),
) : AppState {
}
