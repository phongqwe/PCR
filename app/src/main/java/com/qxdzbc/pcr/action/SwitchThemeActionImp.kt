package com.qxdzbc.pcr.action

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.IsDarkThemeMs
import javax.inject.Inject

class SwitchThemeActionImp @Inject constructor(
    @IsDarkThemeMs val isDarkThemeMs: Ms<Boolean>
) : SwitchThemeAction {
    override fun switchTheme(isDark: Boolean) {
        isDarkThemeMs.value = isDark
    }
}
