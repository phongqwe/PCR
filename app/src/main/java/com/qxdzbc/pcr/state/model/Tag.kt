package com.qxdzbc.pcr.state.model

import com.qxdzbc.pcr.database.model.DbTag

interface Tag {
    val tagId:TagId
    val name:String
    fun toDbTag():DbTag{
        if(this is DbTag){
            return this
        }else{
            return DbTag(
                id = tagId.i, name = name
            )
        }
    }
}

