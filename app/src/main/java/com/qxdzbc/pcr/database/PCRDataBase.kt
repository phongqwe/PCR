package com.qxdzbc.pcr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.model.Entry
import com.qxdzbc.pcr.model.Tag

@Database(
    entities = [Entry::class, Tag::class],
    version =  1
)
abstract class PCRDataBase : RoomDatabase(){
    abstract fun entryDao(): EntryDao
}
