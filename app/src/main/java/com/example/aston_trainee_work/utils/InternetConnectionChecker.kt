package com.example.aston_trainee_work.utils

import android.net.ConnectivityManager
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.aston_trainee_work.common.ArticlesApp

class InternetConnectionChecker {
    companion object {
        fun isConnected(): Boolean {
            var connected = false
            try {
                val context = ArticlesApp.INSTANCE.applicationContext
                val connectivityManager =
                    context.getSystemService(FragmentActivity.CONNECTIVITY_SERVICE)
                            as ConnectivityManager
                val nInfo = connectivityManager.activeNetworkInfo
                connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
                return connected
            } catch (e: Exception) {
                e.message?.let { Log.e("Connectivity Exception", it) }
            }
            return connected
        }
    }
}