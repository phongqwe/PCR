package com.qxdzbc.pcr.action.create_entry

import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import com.github.michaelbull.result.onSuccess
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.di.state.EntryContMs
import com.qxdzbc.pcr.di.state.UserSt
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.model.Entry
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
    override fun createEntryAndWriteToDb(
        date: Date,
        money: Double,
        detail: String?,
        tags: List<Tag>,
        isCost:Boolean
    ) :Rs<Unit,ErrorReport>{
        val rs= ecMs.value.createEntryAndWriteToDb(
            date, money, detail, tags, isCost
        )
        rs.onSuccess {
            ecMs.value = it
        }
        errorRouter.reportToMainScreenIfNeed(rs)
        return rs.map { Unit }
    }

    override suspend fun addEntryAndWriteToDbAndAttemptFirebase(entry: Entry): Rs<Unit, ErrorReport> {
        val rs = ecMs.value.addEntryAndWriteToDb(entry)
        val userId = userSt.value?.uid
        if(userId!=null){
            val rs2=rs.flatMap{
                it.writeUnUploadedToFirestore(userId)
            }.onSuccess {
                ecMs.value = it
            }
            errorRouter.reportToCreateEntryScreenIfNeed(rs2)
            return rs2.map {  }
        }else{
            return rs.map {  }
        }

    }
}
