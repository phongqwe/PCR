package com.qxdzbc.pcr.screen.main_screen.state

import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.container.*
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import java.util.*

interface MainScreenState {
    val errorContainerSt:St<ErrorContainer>
    val entryContainerSt:St<EntryContainer>
    val tagContainerSt:St<TagContainer>
    val userSt:St<FirebaseUserWrapper?>
    val isDark:Boolean
    val mainScreenFilter:EntryFilter

    companion object{
        val forPreview:MainScreenState get(){
            return MainScreenStateImp(
                errorContainerMs = ms(ErrorContainerImp()),
                isDarkSt = ms(true),
                userSt = ms(FirebaseUserWrapper.forPreview),
                entryContainerSt = ms(MockEntryContainer.random()),
                tagContainerSt = ms(MockTagContainer()),
                mainScreenFilterSt = ms(EntryFilter.forPreview())
            )
        }
        const val mainScreenNavTag = "MainScreen_NavTag"
    }
}
