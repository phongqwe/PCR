package com.qxdzbc.pcr.di

import android.content.Context
import com.qxdzbc.pcr.App
import com.qxdzbc.pcr.firestore.FirestoreHelper
import com.qxdzbc.pcr.firestore.FirestoreHelperImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AndroidSystemModule {
    @Binds
    @Singleton
    fun FirebaseHelper(i: FirestoreHelperImp): FirestoreHelper
    companion object{
        @Provides
        fun app(@ApplicationContext context:Context):App{
            return context as App
        }
    }
}
