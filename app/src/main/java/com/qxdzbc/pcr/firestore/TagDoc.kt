package com.qxdzbc.pcr.firestore

import com.google.firebase.firestore.DocumentId
import java.util.UUID

data class TagDoc(
//    @DocumentId
    val id:String="",
    val name:String=""
){
    companion object {
        fun random():TagDoc{
            val id = UUID.randomUUID().toString()
            return TagDoc(id,UUID.randomUUID().toString())
        }
        const val tagColPath = "tags"
    }
}
