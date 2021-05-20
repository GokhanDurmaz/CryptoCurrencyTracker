package com.cryptocurrencytracker.demo.helper.util

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.util.Log
import com.cryptocurrencytracker.demo.App
import com.cryptocurrencytracker.demo.helper.getService
import java.lang.Exception

/**
 * Created by gokhan on 2/9/21.
 */
@Suppress("DEPRECATION")
fun App.isConnectionEligible(): Boolean {

    val wifiConnection = getService<ConnectivityManager>(CONNECTIVITY_SERVICE)
        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.isConnected ?: false

    if (wifiConnection) {
        try {
            val ping = Runtime.getRuntime().exec("/system/bin/ping -c 1 8.8.8.8").waitFor()

            return ping == 0
        } catch (e: Exception) {
            Log.e("CONNECTION_ERROR","${e.message}")
        }
        return false
    } else return false
}
