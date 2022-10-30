package com.qxdzbc.pcr.di.state

import com.qxdzbc.pcr.di.DefaultObjModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DefaultObjModule::class, AppStateModule::class,
        MainStateModule::class, FrontStateModule::class
    ]
)
@InstallIn(SingletonComponent::class)
interface StateModule {

}
