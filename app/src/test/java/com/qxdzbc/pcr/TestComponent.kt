package com.qxdzbc.pcr

import com.qxdzbc.pcr.action.create_entry.CreateEntryActionImp
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.di.DefaultObjModule
import com.qxdzbc.pcr.di.UserMs
import com.qxdzbc.pcr.di.state.StateModule
import com.qxdzbc.pcr.di.action.ActionModule
import com.qxdzbc.pcr.firestore.FirestoreHelper
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import dagger.Component
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@DisableInstallInCheck
@Component(
    modules = [
        StateModule::class,
        DefaultObjModule::class,
        MockDbModule::class,
        ActionModule::class,
        MockAndroidSystemModule::class,
    ]
)
@Singleton
interface TestComponent {
    fun createEntryActionImp(): CreateEntryActionImp
    fun mockEntryDao(): EntryDao
    fun firestoreHelper(): FirestoreHelper
    @UserMs
    fun userMs(): Ms<FirebaseUserWrapper>
}
