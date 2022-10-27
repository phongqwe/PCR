package com.qxdzbc.pcr.state.app

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.screen.front_screen.state.FrontScreenState
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.container.TagContainer

interface AppState {
    suspend fun initLoadData()
    val frontScreenStateMs:Ms<FrontScreenState>
    val mainScreenStateMs:Ms<MainScreenState>
    val entryContainerMs:Ms<EntryContainer>
    val tagContainerMs:Ms<TagContainer>
    val isDarkThemeMs:Ms<Boolean>
    val errorReporter:ErrorRouter
    val userMs:Ms<FirebaseUserWrapper?>
}
