package com.qxdzbc.pcr.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.screen.front_screen.state.FrontScreenState
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState

interface AppState {
    val frontScreenStateMs:Ms<FrontScreenState>
    val mainScreenStateMs:Ms<MainScreenState>
    val isDarkThemeMs:Ms<Boolean>
    val errorReporter:ErrorRouter
}
