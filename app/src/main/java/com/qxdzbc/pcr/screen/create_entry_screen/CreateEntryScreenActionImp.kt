package com.qxdzbc.pcr.screen.create_entry_screen

import com.qxdzbc.pcr.action.remove_err.RemoveErrAction
import com.qxdzbc.pcr.di.action.RemoveErrFromCreateEntryScreenQ
import javax.inject.Inject

class CreateEntryScreenActionImp @Inject constructor(
    @RemoveErrFromCreateEntryScreenQ
    val removeErr:RemoveErrAction
) : CreateEntryScreenAction,
    RemoveErrAction by removeErr
{
}
