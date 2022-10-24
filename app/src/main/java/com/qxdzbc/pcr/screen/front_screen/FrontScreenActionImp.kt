package com.qxdzbc.pcr.screen.front_screen

import com.qxdzbc.pcr.action.RemoveErr
import com.qxdzbc.pcr.action.SwitchThemeAction
import com.qxdzbc.pcr.action.SwitchThemeActionImp
import com.qxdzbc.pcr.di.action.RemoveErrFromFrontScreenQ
import com.qxdzbc.pcr.err.ErrorReport
import javax.inject.Inject

class FrontScreenActionImp @Inject constructor(
    private val switchThemeAction: SwitchThemeAction,
    @RemoveErrFromFrontScreenQ
    private val removeErr: RemoveErr,
) : FrontScreenAction,
    SwitchThemeAction by switchThemeAction,
    RemoveErr by removeErr

