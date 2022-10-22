package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.qxdzbc.pcr.database.model.TagAssignment

@Dao
interface TagAssignmentDao{
    @Insert
    fun insert(vararg tagAssignment: TagAssignment)

    @Delete
    fun delete(tagAssignment: TagAssignment)

    @Query("SELECT * FROM TagAssignment")
    fun getAll():List<TagAssignment>
}
