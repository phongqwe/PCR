package com.qxdzbc.pcr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.database.DbErrors
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.err.ErrorReport

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

    fun deleteRs(entry:DbEntry):Rs<Unit,ErrorReport>{
        try{
            delete(entry)
            return Ok(Unit)
        }catch (e:Throwable){
            return DbErrors.UnableToDeleteEntryFromDb.report().toErr()
        }
    }

    @Update
    fun update(entries: List<DbEntry>)

    /**
     * Process a list of [DbEntry]. Insert the new entries, and update the old entries having the same id as the new ones.
     */
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
