package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.qxdzbc.pcr.model.Tag

@Dao
interface TagDao {
    @Query("SELECT * FROM tag")
    fun getAll():List<Tag>

    @Insert
    fun insert(vararg tags:Tag)

    @Delete
    fun delete(tag:Tag)
}
