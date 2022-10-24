package com.qxdzbc.pcr.screen.front_screen

import com.qxdzbc.pcr.action.OpenAuthUIAction
import com.qxdzbc.pcr.action.SwitchThemeAction

interface FrontScreenAction: SwitchThemeAction {
    companion object{
        val doNothing = FrontScreenActionDoNothing()
    }
}
