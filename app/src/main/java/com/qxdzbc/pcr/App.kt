package com.qxdzbc.pcr

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.database.PcrDatabase
import com.qxdzbc.pcr.di.state.AppStateMs
import com.qxdzbc.pcr.state.app.AppState
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

    override fun onCreate() {
        super.onCreate()
        configEmulator()
    }
    private fun configEmulator(){
//        if(BuildConfig.DEBUG){
//            val firestore = FirebaseFirestore.getInstance()
//            firestore.useEmulator("127.0.0.1", 8080)
//            FirebaseAuth.getInstance().useEmulator("127.0.0.1", 9099)
//            val settings = FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(false)
//                .build()
//            firestore.firestoreSettings = settings
//        }
    }
}
