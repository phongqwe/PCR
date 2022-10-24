package com.qxdzbc.pcr.di.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.screen.front_screen.state.FrontScreenState
import com.qxdzbc.pcr.screen.front_screen.state.FrontScreenStateImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
interface FrontStateModule {

    @Binds
    fun FrontScreenState(i:FrontScreenStateImp):FrontScreenState

    companion object {
        @Provides
        @ErrorContInFrontMs
        @Singleton
        fun ErrorContMs():Ms<ErrorContainer>{
            return ms(ErrorContainerImp())
        }
        @Provides
        @FrontScreeStateMs
        @Singleton
        fun FrontScreeStateMs(i:FrontScreenState):Ms<FrontScreenState>{
            return ms(i)
        }
    }
}
