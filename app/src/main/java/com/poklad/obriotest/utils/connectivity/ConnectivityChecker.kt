package com.poklad.obriotest.utils.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

interface ConnectivityChecker {
    fun isConnected(): Boolean
}