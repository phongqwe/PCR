package com.qxdzbc.pcr.screen.front_screen

import com.qxdzbc.pcr.action.remove_err.RemoveErr
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction

interface FrontScreenAction: SwitchThemeAction, RemoveErr {
    companion object{
        val doNothing = FrontScreenActionDoNothing()
    }
}
