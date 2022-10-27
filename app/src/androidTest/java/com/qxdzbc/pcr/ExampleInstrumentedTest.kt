package com.qxdzbc.pcr

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.firestore.FirebaseFirestore

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.qxdzbc.pcr", appContext.packageName)
    }
    @Test
    fun q(){
//        FirebaseFirestore.getInstance().useEmulator("127.0.0.1", 8080)
        val s = FirebaseFirestore.getInstance()
        s.collection("users").document("user2").set(mapOf(
            "name" to "user2",
            "age" to 123
        )).addOnSuccessListener {
            Log.d("Phong","Success")
        }.addOnFailureListener {
            Log.d("Phong","Fail")
        }

    }
}
