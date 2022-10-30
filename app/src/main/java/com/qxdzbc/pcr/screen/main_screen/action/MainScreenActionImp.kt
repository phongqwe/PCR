package com.qxdzbc.pcr.screen.main_screen.action

import com.qxdzbc.pcr.action.create_entry.CreateEntryAction
import com.qxdzbc.pcr.action.filter_entry.FilterEntryOnMainScreenAction
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction
import com.qxdzbc.pcr.action.upload_entry.UploadEntryAction
import javax.inject.Inject

class MainScreenActionImp @Inject constructor(
    val stAct: SwitchThemeAction,
    val filterAction: FilterEntryOnMainScreenAction,
    val ceAction:CreateEntryAction,
    val uleAction:UploadEntryAction
) : MainScreenAction,
    UploadEntryAction by uleAction,
    SwitchThemeAction by stAct,
    FilterEntryOnMainScreenAction by filterAction,
    CreateEntryAction by ceAction
