package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.qxdzbc.pcr.database.model.Tag
import com.qxdzbc.pcr.database.model.TagWithEntries

@Dao
interface TagDao {
    @Query("SELECT * FROM Tag")
    @Transaction
    fun getTagWithEntries():List<TagWithEntries>

    @Query("SELECT * FROM Tag")
    fun getAll():List<Tag>

    @Insert
    fun insert(vararg tags: Tag)

    @Delete
    fun delete(tag: Tag)
}
