package com.qxdzbc.pcr

import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.test.MockEntryDao
import com.qxdzbc.test.MockTagAssignmentDao
import com.qxdzbc.test.MockTagDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import org.mockito.kotlin.spy
import javax.inject.Singleton

@DisableInstallInCheck
@Module
interface MockDbModule {
    companion object{
        @Provides
        @Singleton
        fun entryDao(): EntryDao{
            val spk = spy(MockEntryDao())
            return spk
        }
        @Provides
        @Singleton
        fun tagDao(): TagDao{
            val i = spy(MockTagDao())
            return i
        }

        @Provides
        @Singleton
        fun tagAssignmentDao(): TagAssignmentDao{
            val i = spy(MockTagAssignmentDao())
            return i
        }
    }





}
