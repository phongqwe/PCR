package com.qxdzbc.pcr.action

import android.util.Log
import javax.inject.Inject


interface Action1 {
    fun doWork()
}

class Action1Imp @Inject constructor(): Action1{
    override fun doWork(){
        Log.d("Phong","Action 1 invoked")
    }
}
