package com.qxdzbc.pcr.action.remove_entry

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.EntryContMs
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.model.Entry
import javax.inject.Inject

class RemoveEntryActionImp @Inject constructor(
    @EntryContMs
    val ecMs:Ms<EntryContainer>
) : RemoveEntryAction {
    override suspend fun removeEntry(e: Entry) {
        ecMs.value.removeEntry(e)
    }
}
