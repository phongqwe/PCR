package com.qxdzbc.pcr.action.create_entry

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import com.google.firebase.auth.FirebaseUser
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.di.UserMs
import com.qxdzbc.pcr.di.state.EntryContMs
import com.qxdzbc.pcr.di.state.UserSt
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
    @UserSt
    val userSt:St<@JvmSuppressWildcards FirebaseUserWrapper?>
) : CreateEntryAction {
    override suspend fun createEntry(
        date: Date,
        money: Double,
        detail: String?,
        tags: List<Tag>,
        isCost:Boolean
    ) {
        val entry = DbEntryWithTags(
            entry = DbEntry(
                id = UUID.randomUUID().toString(),
                money=money,
                detail=detail,
                dateTime = date.time,
                isUploaded = 0,
                isCost = if(isCost) 1 else 0
            ),
            tags = tags.map { it.toDbTag() }
        )
        val rs= ecMs.value.addEntryAndWriteToDb(userSt.value?.uid,entry)
        rs.onSuccess {
            ecMs.value = it
        }.onFailure {
            errorRouter.reportToMainScreen(it)
        }
    }
}
