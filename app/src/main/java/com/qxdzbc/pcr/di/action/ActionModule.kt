package com.qxdzbc.pcr.di.action

import com.qxdzbc.pcr.action.remove_err.RemoveErr
import com.qxdzbc.pcr.action.remove_err.RemoveErrFromFrontScreen
import com.qxdzbc.pcr.action.remove_err.RemoveErrFromMainScreen
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeActionImp
import com.qxdzbc.pcr.action.update_user.UpdateUserAction
import com.qxdzbc.pcr.action.update_user.UpdateUserActionImp
import com.qxdzbc.pcr.screen.front_screen.FrontScreenAction
import com.qxdzbc.pcr.screen.front_screen.FrontScreenActionImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ActionModule {
    @Binds
    @Singleton
    fun UpdateUserAction(i: UpdateUserActionImp): UpdateUserAction

    @Binds
    @Singleton
    @RemoveErrFromFrontScreenQ
    fun RemoveErrInFrontAct(i: RemoveErrFromFrontScreen): RemoveErr

    @Binds
    @Singleton
    @RemoveErrFromMainScreenQ
    fun RemoveErrInMainAct(i: RemoveErrFromMainScreen): RemoveErr

    @Binds
    @Singleton
    fun frontScreenAct(i: FrontScreenActionImp): FrontScreenAction

    @Binds
    @Singleton
    fun switchThemeAct(i: SwitchThemeActionImp): SwitchThemeAction
}
