package com.qxdzbc.pcr.action.filter_entry

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.MainScreenFilterMs
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import javax.inject.Inject

class FilterEntryOnMainScreenActionImp @Inject constructor(
    @MainScreenFilterMs
    val filterMs:Ms<EntryFilter?>,
) : FilterEntryOnMainScreenAction {
    override fun filter(filter: EntryFilter) {
        filterMs.value = filter
    }
}
