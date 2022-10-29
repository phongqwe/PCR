package com.qxdzbc.pcr.screen.front_screen

import com.qxdzbc.pcr.action.remove_err.RemoveErrAction
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction
import com.qxdzbc.pcr.di.action.RemoveErrFromFrontScreenQ
import javax.inject.Inject

class FrontScreenActionImp @Inject constructor(
    private val switchThemeAction: SwitchThemeAction,
    @RemoveErrFromFrontScreenQ
    private val removeErrAction: RemoveErrAction,
) : FrontScreenAction,
    SwitchThemeAction by switchThemeAction,
    RemoveErrAction by removeErrAction

