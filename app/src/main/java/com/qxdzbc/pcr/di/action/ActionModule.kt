package com.qxdzbc.pcr.di.action

import com.qxdzbc.pcr.action.create_entry.CreateEntryAction
import com.qxdzbc.pcr.action.create_entry.CreateEntryActionImp
import com.qxdzbc.pcr.action.filter_entry.FilterEntryOnMainScreenAction
import com.qxdzbc.pcr.action.filter_entry.FilterEntryOnMainScreenActionImp
import com.qxdzbc.pcr.action.remove_err.RemoveErrAction
import com.qxdzbc.pcr.action.remove_err.RemoveErrFromCreateEntryScreen
import com.qxdzbc.pcr.action.remove_err.RemoveErrFromFrontScreen
import com.qxdzbc.pcr.action.remove_err.RemoveErrFromMainScreen
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeAction
import com.qxdzbc.pcr.action.switch_theme.SwitchThemeActionImp
import com.qxdzbc.pcr.action.update_user.UpdateUserAction
import com.qxdzbc.pcr.action.update_user.UpdateUserActionImp
import com.qxdzbc.pcr.screen.create_entry.CreateEntryScreenAction
import com.qxdzbc.pcr.screen.create_entry.CreateEntryScreenActionImp
import com.qxdzbc.pcr.screen.front_screen.FrontScreenAction
import com.qxdzbc.pcr.screen.front_screen.FrontScreenActionImp
import com.qxdzbc.pcr.screen.main_screen.action.MainScreenAction
import com.qxdzbc.pcr.screen.main_screen.action.MainScreenActionImp
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
    fun CreateEntryScreenAction(i:CreateEntryScreenActionImp):CreateEntryScreenAction

    @Binds
    @Singleton
    fun CreateEntryAction(i: CreateEntryActionImp):CreateEntryAction

    @Binds
    @Singleton
    fun FilterEntryOnMainScreenAction(i: FilterEntryOnMainScreenActionImp):FilterEntryOnMainScreenAction

    @Binds
    @Singleton
    fun MainScreenAction(i:MainScreenActionImp): MainScreenAction

    @Binds
    @Singleton
    fun UpdateUserAction(i: UpdateUserActionImp): UpdateUserAction

    @Binds
    @Singleton
    @RemoveErrFromFrontScreenQ
    fun RemoveErrInFrontAct(i: RemoveErrFromFrontScreen): RemoveErrAction

    @Binds
    @Singleton
    @RemoveErrFromCreateEntryScreenQ
    fun RemoveErrInCreateEntryScreenAct(i: RemoveErrFromCreateEntryScreen): RemoveErrAction

    @Binds
    @Singleton
    @RemoveErrFromMainScreenQ
    fun RemoveErrInMainAct(i: RemoveErrFromMainScreen): RemoveErrAction

    @Binds
    @Singleton
    fun frontScreenAct(i: FrontScreenActionImp): FrontScreenAction

    @Binds
    @Singleton
    fun switchThemeAct(i: SwitchThemeActionImp): SwitchThemeAction
}
