package com.qxdzbc.pcr.action.upload_entry

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport

interface UploadEntryAction {
    fun uploadAllEntries():Rs<Unit,ErrorReport>
    fun uploadUnUploadedEntries():Rs<Unit,ErrorReport>
}
