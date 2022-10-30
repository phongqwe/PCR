package com.qxdzbc.pcr.action.remove_entry

import com.qxdzbc.pcr.state.model.Entry

interface RemoveEntryAction {
    suspend fun removeEntry(e:Entry)
}
