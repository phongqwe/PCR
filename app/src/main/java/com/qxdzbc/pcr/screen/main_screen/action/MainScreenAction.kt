package com.qxdzbc.pcr.screen.main_screen.action

import com.qxdzbc.pcr.action.create_entry.CreateEntryAction
import com.qxdzbc.pcr.action.filter_entry.FilterEntryOnMainScreenAction
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction

interface MainScreenAction :SwitchThemeAction, FilterEntryOnMainScreenAction,CreateEntryAction {
    companion object{
        val forPreview:MainScreenAction = MainScreenActionDoNothing
    }
}
