package com.qxdzbc.pcr.action.switch_loading_state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.IsMainScreenLoadingMs
import com.qxdzbc.pcr.di.state.IsTagScreenLoadingMs
import javax.inject.Inject

class SwitchLoadingStateActionImp @Inject constructor(
    @IsMainScreenLoadingMs
    val mainLoadingMs:Ms<Boolean>,
    @IsTagScreenLoadingMs
    val tagLoadingMs:Ms<Boolean>,
) : SwitchLoadingStateAction {
    override fun setMainScreenLoadingState(i: Boolean) {
        mainLoadingMs.value = i
    }

    override fun setTagScreenLoadingState(i: Boolean) {
        tagLoadingMs.value = i
    }
}
