package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags

@Dao
interface EntryDao{
    @Transaction
    @Query("SELECT * FROM ${DbEntry.tableName}")
    fun getEntryWithTag():List<DbEntryWithTags>

    @Query("SELECT * FROM ${DbEntry.tableName}")
    fun getAll():List<DbEntry>

    @Insert
    fun insert(vararg entries: DbEntry)

    @Delete
    fun delete(entry: DbEntry)
}
