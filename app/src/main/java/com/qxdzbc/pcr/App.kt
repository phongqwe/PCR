package com.qxdzbc.pcr

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.google.firebase.auth.FirebaseAuth
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.database.PcrDatabase
import com.qxdzbc.pcr.di.state.AppStateMs
import com.qxdzbc.pcr.state.app.AppState
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper.Companion.toWrapper
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
    private val appStateMs get()=_appStateMs

    override fun onCreate() {
        super.onCreate()
        MainScope().launch(Dispatchers.Default) {
            appStateMs.value.initLoadData()
        }
        configFirebaseAuthListener()
        configNetworkListener()
    }

    private fun configFirebaseAuthListener(){
        appStateMs.value.userMs.value = FirebaseAuth.getInstance().currentUser?.toWrapper()
        FirebaseAuth.getInstance().addAuthStateListener {
            appStateMs.value.userMs.value = it.currentUser?.toWrapper()
        }
    }

    private fun configNetworkListener(){
        val hasNetWorkMs = appStateMs.value.hasNetworkConnectionMs
        val networkCb = object: NetworkCallback() {
            override fun onAvailable(network: Network) {
                hasNetWorkMs.value = true
            }

            override fun onLost(network: Network) {
                hasNetWorkMs.value = false
            }

            override fun onUnavailable() {
                hasNetWorkMs.value = false
            }
        }
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.requestNetwork(networkRequest,networkCb)
    }
}
