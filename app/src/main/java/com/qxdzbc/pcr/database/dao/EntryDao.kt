package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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
    @Insert
    fun insert(entries: List<DbEntry>)
    @Delete
    fun delete(entry: DbEntry)

    @Update
    fun update(entries: List<DbEntry>)

    @Transaction
    fun insertOrUpdate(i: List<DbEntry>) {
        val allEntries = getAll().associateBy { it.id }
        val allKeys = allEntries.keys
        val insertTarget = i.filter { it.id !in allKeys }
        val updateTargets = i.filter { it.id in allKeys }
        insert(insertTarget)
        update(updateTargets)
    }
}
