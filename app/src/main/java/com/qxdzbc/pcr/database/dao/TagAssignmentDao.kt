package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.qxdzbc.pcr.database.model.DbTagAssignment

@Dao
interface TagAssignmentDao{
    @Insert
    fun insert(vararg tagAssignment: DbTagAssignment)

    @Delete
    fun delete(tagAssignment: DbTagAssignment)

    @Query("SELECT * FROM ${DbTagAssignment.tableName}")
    fun getAll():List<DbTagAssignment>
}
