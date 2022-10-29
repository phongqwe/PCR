package com.qxdzbc.pcr.di.state

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.St
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.err.ErrorContainer
import com.qxdzbc.pcr.err.ErrorContainerImp
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenState
import com.qxdzbc.pcr.screen.main_screen.state.MainScreenStateImp
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.container.EntryContainerImp
import com.qxdzbc.pcr.state.container.TagContainer
import com.qxdzbc.pcr.state.container.TagContainerImp
import com.qxdzbc.pcr.state.container.filter.EntryFilter
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
        @Singleton
        @MainScreenFilterMs
        fun MainScreenFilterMs():Ms<EntryFilter?>{
            return ms(null)
        }

        @Provides
        @Singleton
        @MainScreenFilterSt
        fun MainScreenFilterSt(@MainScreenFilterMs i:Ms<EntryFilter?> ):St<EntryFilter?>{
            return i
        }



        @Provides
        @Singleton
        @EntryContMs
        fun EntryContainerMs(i: EntryContainerImp): Ms<EntryContainer>{
            return ms(i)
        }

        @Provides
        @Singleton
        @EntryContSt
        fun EntryContainerSt(@EntryContMs i: Ms<EntryContainer>): St<EntryContainer>{
            return i
        }

        @Provides
        @Singleton
        @TagContMs
        fun TagContainerMs(i: TagContainerImp): Ms<TagContainer>{
            return ms(i)
        }

        @Provides
        @Singleton
        @TagContSt
        fun TagContainerSt(@TagContMs i: Ms<TagContainer>): St<TagContainer>{
            return i
        }

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

        @Provides
        @ErrorContInCreateEntryScreenMs
        @Singleton
        fun ErrorContInEntryCreateMs():Ms<ErrorContainer>{
            return ms(ErrorContainerImp())
        }
    }

}
