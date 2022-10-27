package com.qxdzbc.pcr.di

import androidx.room.Room
import com.qxdzbc.pcr.App
import com.qxdzbc.pcr.database.AbsPcrDataBase
import com.qxdzbc.pcr.database.PcrDatabase
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.dao.TagDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DbModule {
    companion object{
        @Provides
        @Singleton
        fun db(app:App): PcrDatabase {
            return Room.databaseBuilder(app, AbsPcrDataBase::class.java, AbsPcrDataBase.dbName)
                .createFromAsset("initDb.db")
                .build()
        }

        @Provides
        @Singleton
        fun entryDao(db:PcrDatabase):EntryDao{
            return db.entryDao
        }

        @Provides
        @Singleton
        fun tagDao(db:PcrDatabase):TagDao{
            return db.tagDao
        }

        @Provides
        @Singleton
        fun tagAssignmentDao(db:PcrDatabase):TagAssignmentDao{
            return db.tagAssignmentDao
        }
    }
}
