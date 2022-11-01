package com.qxdzbc.pcr.di.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@DisableInstallInCheck
@Module
interface TagScreenStateModule {
    companion object{

        @IsTagScreenLoadingMs
        @Provides
        @Singleton
        fun isMainScreenLoadingMs():Ms<Boolean>{
            return StateUtils.ms(false)
        }

        @IsTagScreenLoadingSt
        @Provides
        @Singleton
        fun isMainScreenLoadingSt(@IsMainScreenLoadingMs i:Ms<Boolean>):St<Boolean>{
            return i
        }
    }
}
