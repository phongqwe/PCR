package com.qxdzbc.pcr.di.action

import com.qxdzbc.pcr.action.Action1
import com.qxdzbc.pcr.action.Action1Imp
import com.qxdzbc.pcr.action.SwitchThemeAction
import com.qxdzbc.pcr.action.SwitchThemeActionImp
import com.qxdzbc.pcr.screen.front_screen.FrontScreenAction
import com.qxdzbc.pcr.screen.front_screen.FrontScreenActionImp
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

    @Binds
    @Singleton
    fun frontScreenAct(i: FrontScreenActionImp): FrontScreenAction

    @Binds
    @Singleton
    fun switchThemeAct(i:SwitchThemeActionImp): SwitchThemeAction
}
