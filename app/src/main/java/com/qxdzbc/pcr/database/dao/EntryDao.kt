package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.qxdzbc.pcr.database.model.Entry
import com.qxdzbc.pcr.database.model.EntryWithTags

@Dao
interface EntryDao{
    @Transaction
    @Query("SELECT * FROM Entry")
    fun getEntryWithTag():List<EntryWithTags>

    @Query("SELECT * FROM Entry")
    fun getAll():List<Entry>

    @Insert
    fun insert(vararg entries: Entry)

    @Delete
    fun delete(entry: Entry)
}
