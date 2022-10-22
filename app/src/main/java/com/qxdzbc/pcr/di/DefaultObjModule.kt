package com.qxdzbc.pcr.di

import com.qxdzbc.pcr.database.model.Entry
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
        fun DefaultEntryMap():Map<String,Entry> = emptyMap()
    }


}
