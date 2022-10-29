package com.qxdzbc.pcr.action.remove_err

import com.qxdzbc.pcr.err.ErrorReport

interface RemoveErrAction {
    fun removeErr(err:ErrorReport)
}
