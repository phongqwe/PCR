package com.qxdzbc.pcr

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Bench {
    @Test
    fun localDateTimeToIsoDate_in_sqlite() {
        val d = LocalDateTime.now()
        val dStr = d.format(DateTimeFormatter.ISO_DATE_TIME)
        val d2 = LocalDateTime.parse(dStr,DateTimeFormatter.ISO_DATE_TIME)
        assertEquals(d,d2)
    }
    @Test
    fun q(){
//        FirebaseFirestore.getInstance().useEmulator("127.0.0.1", 8080)
//        val s = FirebaseFirestore.getInstance()
//        s.collection("users").document("user1").set(mapOf("name" to "abc"))
    }
}
