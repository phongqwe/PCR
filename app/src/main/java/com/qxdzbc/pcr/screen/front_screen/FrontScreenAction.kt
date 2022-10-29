package com.qxdzbc.pcr.screen.front_screen

import com.qxdzbc.pcr.action.remove_err.RemoveErrAction
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction

interface FrontScreenAction: SwitchThemeAction, RemoveErrAction {
    companion object{
        val doNothing = FrontScreenActionDoNothing()
    }
}
