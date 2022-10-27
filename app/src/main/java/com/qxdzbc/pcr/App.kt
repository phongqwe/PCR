package com.qxdzbc.pcr

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.database.PcrDatabase
import com.qxdzbc.pcr.di.state.AppStateMs
import com.qxdzbc.pcr.state.app.AppState
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class App : Application(){
    @Inject lateinit var _pcrDb:PcrDatabase
    val pcrDb get() = _pcrDb

    @Inject
    @AppStateMs
    lateinit var _appStateMs:Ms<AppState>
    val appStateMs get()=_appStateMs
    private var appState by appStateMs
    override fun onCreate() {
        super.onCreate()
        MainScope().launch(Dispatchers.Default) {
            appState.initLoadData()
        }
    }
}
