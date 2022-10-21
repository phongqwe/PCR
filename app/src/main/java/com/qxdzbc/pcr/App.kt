package com.qxdzbc.pcr

import android.app.Application
import androidx.room.Room
import com.qxdzbc.pcr.database.PcrDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    lateinit var _pcrDb:PcrDataBase
    val pcrDb get() = _pcrDb

    override fun onCreate() {
        super.onCreate()
        _pcrDb = Room.databaseBuilder(this,PcrDataBase::class.java,PcrDataBase.dbName)
            .createFromAsset("initDb")
            .build()
    }
}
