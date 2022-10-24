package com.qxdzbc.pcr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.database.model.DbTagAssignment

@Database(
    entities = [DbEntry::class, DbTag::class, DbTagAssignment::class],
    version =  1
)
abstract class AbsPcrDataBase : RoomDatabase(),PcrDatabase{

    companion object{
        const val dbName = "PcrDatabase"
    }
}

