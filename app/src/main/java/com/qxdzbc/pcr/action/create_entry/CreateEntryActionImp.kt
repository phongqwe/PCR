package com.qxdzbc.pcr.action.create_entry

import com.github.michaelbull.result.map
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import com.google.firebase.auth.FirebaseUser
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.di.UserMs
import com.qxdzbc.pcr.di.state.EntryContMs
import com.qxdzbc.pcr.di.state.UserSt
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.model.Tag
import java.util.*
import javax.inject.Inject

class CreateEntryActionImp @Inject constructor(
    @EntryContMs
    val ecMs:Ms<EntryContainer>,
    val errorRouter: ErrorRouter,
) : CreateEntryAction {
    override fun createEntryAndWriteToDbAndAttemptFirebase(
        date: Date,
        money: Double,
        detail: String?,
        tags: List<Tag>,
        isCost:Boolean
    ) :Rs<Unit,ErrorReport>{
        val rs= ecMs.value.createEntryAndWriteToDb(
            date, money, detail, tags, isCost
        )
        errorRouter.reportToMainScreenIfNeed(rs)
        return rs.map { Unit }
    }
}
