package com.qxdzbc.pcr.di

import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbTag
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DefaultObjModule {
    companion object{
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
