package com.qxdzbc.pcr.screen.main_screen.action

import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction

interface MainScreenAction :SwitchThemeAction{
    companion object{
        val forPreview:MainScreenAction = MainScreenActionDoNothing
    }
}
