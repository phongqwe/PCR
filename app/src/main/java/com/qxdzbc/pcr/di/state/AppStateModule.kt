package com.qxdzbc.pcr.di.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.err.ErrorRouter
import com.qxdzbc.pcr.err.ErrorRouterImp
import com.qxdzbc.pcr.state.AppState
import com.qxdzbc.pcr.state.AppStateImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton


@Module
@DisableInstallInCheck
interface AppStateModule {
    @Binds
    fun AppState(i:AppStateImp):AppState

    @Binds
    @Singleton
    fun ErrorReporter(i:ErrorRouterImp):ErrorRouter

    companion object{
        @Provides
        @AppStateMs
        @Singleton
        fun AppStateMs(a:AppState):Ms<AppState>{
            return ms(a)
        }
        @Provides
        @IsDarkThemeMs
        @Singleton
        fun IsDarkThemeMs():Ms<Boolean>{
            return ms(false)
        }

        @Provides
        @IsDarkThemeSt
        @Singleton
        fun IsDarkThemeSt(@IsDarkThemeMs i:Ms<Boolean>):St<Boolean>{
            return i
        }
    }
}
