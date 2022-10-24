package com.qxdzbc.pcr.di.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenStateImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
interface MainStateModule {

    @Binds
    fun MainState(i:MainScreenStateImp):MainScreenState

    companion object{
        @Provides
        @MainScreenStateMs
        @Singleton
        fun MainStateMs(i: MainScreenState):Ms<MainScreenState>{
            return ms(i)
        }
        @Provides
        @ErrorContInMainMs
        @Singleton
        fun ErrorContInMainMs():Ms<ErrorContainer>{
            return ms(ErrorContainerImp())
        }
    }

}
