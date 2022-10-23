package com.qxdzbc.pcr.state.entry

import com.qxdzbc.pcr.database.model.DbTag
import java.util.*

interface Entry {
    val id:String
    val money: Double
    val detail:String?
    val dateTime: Date
    val tags:List<DbTag>
}
