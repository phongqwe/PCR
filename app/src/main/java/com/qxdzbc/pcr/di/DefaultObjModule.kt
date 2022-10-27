package com.qxdzbc.pcr.di

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.di.state.UserSt
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
//@InstallIn(SingletonComponent::class)
interface DefaultObjModule {
    companion object{

        @Provides
        @False
        fun False():Boolean {
            return false
        }

        @Provides
        @DefaultEntryMap
        fun DefaultEntryMap():Map<String, Entry> {
            return emptyMap()
        }

        @Provides
        @DefaultTagMap
        fun DefaultTagMap():Map<String, Tag> {
            return emptyMap()
        }
    }


}
