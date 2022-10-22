package com.qxdzbc.pcr

import android.app.Application
import com.qxdzbc.pcr.database.AbsPcrDataBase
import com.qxdzbc.pcr.database.PcrDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(){
    @Inject lateinit var _pcrDb:PcrDatabase
    val pcrDb get() = _pcrDb
}
