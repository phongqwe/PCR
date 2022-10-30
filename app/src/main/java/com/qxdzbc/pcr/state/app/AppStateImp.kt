package com.qxdzbc.pcr.state.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.github.michaelbull.result.map
import com.github.michaelbull.result.onSuccess
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.di.False
import com.qxdzbc.pcr.di.FalseMs
import com.qxdzbc.pcr.di.UserMs
import com.qxdzbc.pcr.di.state.*
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.screen.front_screen.state.FrontScreenState
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.container.TagContainer
import javax.inject.Inject

data class AppStateImp @Inject constructor(
    @FrontScreeStateMs
    override val frontScreenStateMs:Ms<FrontScreenState>,
    @MainScreenStateMs
    override val mainScreenStateMs:Ms<MainScreenState>,
    override val errorReporter: ErrorRouter,
    @IsDarkThemeMs
    override val isDarkThemeMs: Ms<Boolean>,
    @UserMs
    override val userMs:Ms<FirebaseUserWrapper?> = ms(null),
    @EntryContMs
    override val entryContainerMs: Ms<EntryContainer>,
    @TagContMs
    override val tagContainerMs: Ms<TagContainer>,
    @HasNetworkConnectionMs
    override val hasNetworkConnectionMs: Ms<Boolean>,
) : AppState {
    override suspend fun initLoadData() {
        val user by userMs
        entryContainerMs.value = entryContainerMs.value.initLoad(user?.uid)
        tagContainerMs.value = tagContainerMs.value.initLoad(user?.uid)
    }
}
