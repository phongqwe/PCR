package com.qxdzbc.pcr.di

import com.qxdzbc.pcr.di.state.AppStateModule
import com.qxdzbc.pcr.di.state.FrontStateModule
import com.qxdzbc.pcr.di.state.MainStateModule
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.container.EntryContainerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DefaultObjModule::class, AppStateModule::class,
        MainStateModule::class, FrontStateModule::class
    ]
)
@InstallIn(SingletonComponent::class)
interface StateModule {
    @Binds
    @Singleton
    fun EntryContainer(i: EntryContainerImp): EntryContainer
}
