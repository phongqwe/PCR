package com.qxdzbc.pcr.action.create_entry

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Tag
import java.util.Date

interface CreateEntryAction {
    suspend fun createEntry(
        date:Date,money:Double,
        detail:String?,tags:List<Tag>,
        isCost:Boolean
    )
}
