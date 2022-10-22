package com.qxdzbc.pcr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.database.model.Tag
import com.qxdzbc.pcr.database.model.TagAssignment

@Database(
    entities = [Entry::class, Tag::class, TagAssignment::class],
    version =  1
)
abstract class AbsPcrDataBase : RoomDatabase(),PcrDatabase{

    companion object{
        val dbName = "PcrDatabase"
    }
}

