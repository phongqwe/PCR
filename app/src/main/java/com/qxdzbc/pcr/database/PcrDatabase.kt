package com.qxdzbc.pcr.database

import com.qxdzbc.pcr.database.dao.EntryDao
import com.qxdzbc.pcr.database.dao.TagAssignmentDao
import com.qxdzbc.pcr.database.dao.TagDao

interface PcrDatabase{
    val entryDao: EntryDao
    val tagDao: TagDao
    val tagAssignmentDao: TagAssignmentDao
    fun deleteEverything()
}
