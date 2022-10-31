package com.qxdzbc.pcr.action.create_entry

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import java.util.Date

interface CreateEntryAction {
    /**
     * Create an entry using the input information, add that to the appropriate container and db.
     */
    fun createEntryAndWriteToDb(
        date:Date,
        money:Double,
        detail:String?,
        tags:List<Tag>,
        isCost:Boolean
    ): Rs<Unit, ErrorReport>

    suspend fun addEntryAndWriteToDbAndAttemptFirebase(entry:Entry): Rs<Unit, ErrorReport>
}
