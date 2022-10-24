package com.qxdzbc.pcr

import android.app.Application
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.database.AbsPcrDataBase
import com.qxdzbc.pcr.database.PcrDatabase
import com.qxdzbc.pcr.di.state.AppStateMs
import com.qxdzbc.pcr.state.AppState
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(){
    @Inject lateinit var _pcrDb:PcrDatabase
    val pcrDb get() = _pcrDb

    @Inject
    @AppStateMs
    lateinit var _appStateMs:Ms<AppState>
    val appStateMs get()=_appStateMs
}
