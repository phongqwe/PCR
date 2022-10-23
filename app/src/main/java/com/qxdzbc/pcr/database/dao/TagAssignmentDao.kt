package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.qxdzbc.pcr.database.model.DbTagAssignment

@Dao
interface TagAssignmentDao{
    @Insert
    fun insert(vararg tagAssignment: DbTagAssignment)

    @Insert
    fun insert(tagAssignments: List<DbTagAssignment>)

    @Delete
    fun delete(vararg tagAssignment: DbTagAssignment)

    @Delete
    fun delete(tagAssignment: List<DbTagAssignment>)

    @Query("SELECT * FROM ${DbTagAssignment.tableName}")
    fun getAll():List<DbTagAssignment>

    @Update
    fun update(tagAssignments: List<DbTagAssignment>)

    @Transaction
    fun insertOrDelete(l: List<DbTagAssignment>) {
        val newAssignmentsByEntryId = l.groupBy { it.entryId }
        val currentAssignments= getAll()
        val currentAssignmentsByEntryId = currentAssignments.groupBy { it.entryId }

        val (oldAssignments,newAssignments) = l.partition { it.entryId in currentAssignmentsByEntryId.keys }

        val inserts = newAssignments.toMutableSet()
        val deletes = mutableSetOf<DbTagAssignment>()

        for(oa in oldAssignments){
            val entryId:String = oa.entryId
            val oldAssigAtEntry:List<DbTagAssignment> = currentAssignmentsByEntryId[entryId]!!
            val newAssigAtEntry = newAssignmentsByEntryId[entryId]
            newAssigAtEntry?.also {
                deletes.addAll(oldAssigAtEntry.filter { it !in newAssigAtEntry})
                inserts.addAll(newAssigAtEntry.filter{it !in oldAssigAtEntry})
            }
        }
        delete(deletes.toList())
        insert(inserts.toList())
    }
}
