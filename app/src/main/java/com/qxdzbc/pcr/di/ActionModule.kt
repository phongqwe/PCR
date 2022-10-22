package com.qxdzbc.pcr.di

import com.qxdzbc.pcr.action.Action1
import com.qxdzbc.pcr.action.Action1Imp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ActionModule {
    @Binds
    @Singleton
    fun act1(i:Action1Imp):Action1
}
