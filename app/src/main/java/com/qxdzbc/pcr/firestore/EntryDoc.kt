package com.qxdzbc.pcr.firestore

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import java.util.UUID

data class EntryDoc(
//    @DocumentId
    val id:String="",
    val detail:String?="",
    val money:Double=0.0,
    val date:Long=0,
    val tagRefs:List<DocumentReference> = emptyList()
){
    companion object{
        const val entriesColPath = "entries"
        fun random():EntryDoc{
            return EntryDoc(
                id = UUID.randomUUID().toString(),
                detail = UUID.randomUUID().toString(),
                money = (1 .. 100).random().toDouble(),
                date = (1 .. 100).random().toLong(),
            )
        }
    }
}
