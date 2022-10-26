package com.qxdzbc.pcr.screen.main_screen.action

import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction
import javax.inject.Inject

class MainScreenActionImp @Inject constructor(
    val stAct: SwitchThemeAction
) : MainScreenAction,
    SwitchThemeAction by stAct
