package com.qxdzbc.pcr

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.UserMs
import com.qxdzbc.pcr.firestore.FirestoreHelper
import com.qxdzbc.pcr.firestore.MockFirestoreHelper
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import org.mockito.kotlin.spy
import javax.inject.Singleton

@Module
@DisableInstallInCheck
interface MockAndroidSystemModule {
    companion object {
        @Provides
        @Singleton
        fun FirebaseHelper(): FirestoreHelper {
            val rt = spy(MockFirestoreHelper())
            return rt
        }
    }
}
