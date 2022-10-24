package com.qxdzbc.pcr.screen.front_screen

import com.qxdzbc.pcr.action.SwitchThemeAction
import com.qxdzbc.pcr.action.SwitchThemeActionImp
import javax.inject.Inject

class FrontScreenActionImp @Inject constructor(
    val switchThemeAction: SwitchThemeAction
) : FrontScreenAction,
    SwitchThemeAction by switchThemeAction {

}
