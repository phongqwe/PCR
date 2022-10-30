package com.qxdzbc.pcr.action.upload_entry

import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import com.github.michaelbull.result.onSuccess
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.di.state.EntryContMs
import com.qxdzbc.pcr.di.state.UserSt
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.firestore.FirestoreHelper
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.model.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UploadEntryActionImp @Inject constructor(
    val fh: FirestoreHelper,
    @UserSt
    val userSt: St<@JvmSuppressWildcards FirebaseUserWrapper>,
    @EntryContMs
    val ecMs: Ms<EntryContainer>,
) : UploadEntryAction {
    override suspend fun uploadEntry(entry: Entry): Rs<Unit, ErrorReport> {
        return withContext(Dispatchers.Default){
            val rs = fh.writeEntry(userSt.value.uid, entry)
            val rt = rs.flatMap {
                ecMs.value.addOrReplaceAndWriteToDb(it).map{
                    ecMs.value = it
                }
            }.map { Unit }
            rt
        }
    }
}
