package com.qxdzbc.pcr.di

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.di.state.UserSt
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DefaultObjModule {
    companion object{

        @Provides
        @False
        fun False():Boolean {
            return false
        }

        @DefaultEntryMap
        @Provides
        fun DefaultEntryMap():Map<String,DbEntry> {
            return emptyMap()
        }
        @DefaultTagMap
        fun DefaultTagMap():Map<String,DbTag> {
            return emptyMap()
        }
    }


}
