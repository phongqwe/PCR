package com.qxdzbc.pcr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.model.Entry
import com.qxdzbc.pcr.model.Tag
import com.qxdzbc.pcr.model.TagAssignment

@Database(
    entities = [Entry::class, Tag::class,TagAssignment::class],
    version =  1
)
abstract class PcrDataBase : RoomDatabase(){

    companion object{
        val dbName = "PcrDatabase"
    }

    abstract fun entryDao(): EntryDao
    abstract fun tagDao():TagDao
    abstract fun tagAssignmentDao(): TagAssignmentDao
}

