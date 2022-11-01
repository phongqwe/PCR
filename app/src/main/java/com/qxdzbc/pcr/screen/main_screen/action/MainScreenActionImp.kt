package com.qxdzbc.pcr.screen.main_screen.action

import com.qxdzbc.pcr.action.create_entry.CreateEntryAction
import com.qxdzbc.pcr.action.filter_entry.FilterEntryOnMainScreenAction
import com.qxdzbc.pcr.action.log_out.LogoutAction
import com.qxdzbc.pcr.action.remove_entry.RemoveEntryAction
import com.qxdzbc.pcr.action.switch_loading_state.SwitchLoadingStateAction
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction
import com.qxdzbc.pcr.action.upload_entry.UploadEntryAction
import javax.inject.Inject

class MainScreenActionImp @Inject constructor(
    val stAct: SwitchThemeAction,
    val filterAction: FilterEntryOnMainScreenAction,
    val ceAction:CreateEntryAction,
    val uleAction:UploadEntryAction,
    val reAct: RemoveEntryAction,
    val lact:LogoutAction,
    val slAct: SwitchLoadingStateAction
) : MainScreenAction,
    SwitchLoadingStateAction by slAct,
    LogoutAction by lact,
    RemoveEntryAction by reAct,
    UploadEntryAction by uleAction,
    SwitchThemeAction by stAct,
    FilterEntryOnMainScreenAction by filterAction,
    CreateEntryAction by ceAction
