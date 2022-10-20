package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.qxdzbc.pcr.model.Entry

@Dao
interface EntryDao{
    @Query("SELECT * FROM entry")
    fun getAll():List<Entry>

    @Insert
    fun insertAll(vararg entries: Entry)

    @Delete
    fun delete(entry: Entry)
}
