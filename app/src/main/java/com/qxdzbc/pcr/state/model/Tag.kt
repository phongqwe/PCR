package com.qxdzbc.pcr.state.model

import com.qxdzbc.pcr.database.model.DbTag

interface Tag {
    val id:String
    val name:String
    fun toDbModel():DbTag{
        if(this is DbTag){
            return this
        }else{
            return DbTag(
                id = id, name = name
            )
        }
    }
}

