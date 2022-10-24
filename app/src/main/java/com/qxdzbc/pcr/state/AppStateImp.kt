package com.qxdzbc.pcr.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.di.False
import com.qxdzbc.pcr.di.state.ErrorContInFrontMs
import com.qxdzbc.pcr.di.state.FrontScreeStateMs
import com.qxdzbc.pcr.di.state.IsDarkThemeMs
import com.qxdzbc.pcr.di.state.MainScreenStateMs
import com.qxdzbc.pcr.err.ErrorContainer
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
) : AppState
