package com.qxdzbc.pcr.screen.main_screen.state

import androidx.compose.runtime.getValue
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.di.state.*
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.container.TagContainer
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import javax.inject.Inject

class MainScreenStateImp @Inject constructor(
    @ErrorContInMainMs
    val errorContainerMs: Ms<ErrorContainer>,
    @IsDarkThemeSt
    private val isDarkSt: St<Boolean>,
    @UserSt
    override val userSt: St<@JvmSuppressWildcards FirebaseUserWrapper?>,
    @EntryContSt
    override val entryContainerSt: St<@JvmSuppressWildcards EntryContainer>,
    @TagContSt
    override val tagContainerSt: St<@JvmSuppressWildcards TagContainer>,
    @MainScreenFilterSt
    val mainScreenFilterSt: St<@JvmSuppressWildcards EntryFilter?>,
) : MainScreenState {
    override val errorContainerSt: St<ErrorContainer>
        get() = errorContainerMs
    override val isDark: Boolean by isDarkSt
    override val mainScreenFilter: EntryFilter?
        get() = mainScreenFilterSt.value
}
