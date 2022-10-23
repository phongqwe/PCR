package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.database.model.DbTagWithEntries

@Dao
interface TagDao {
    @Query("SELECT * FROM ${DbTag.tableName}")
    @Transaction
    fun getTagWithEntries():List<DbTagWithEntries>

    @Query("SELECT * FROM ${DbTag.tableName}")
    fun getAll():List<DbTag>

    @Insert
    @Throws(Exception::class)
    fun insert(vararg tags: DbTag)

    @Delete
    fun delete(tag: DbTag)
}
