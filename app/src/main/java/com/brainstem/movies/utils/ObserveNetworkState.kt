package com.google.movies.utils

import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class ObserveNetworkState(private val connectivityManager: ConnectivityManager): LiveData<Boolean>() {

    constructor(context: Context) : this(context.getSystemService(
        Context
            .CONNECTIVITY_SERVICE)
            as ConnectivityManager)

    // check for network availability
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            //4
            postValue(true)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val isInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

            Log.d(ContentValues.TAG, "networkCapabilities: ${network} $networkCapabilities")

            val isValidated = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

            if (isValidated){
                Log.d(ContentValues.TAG, "hasCapability: ${network} $networkCapabilities")
            } else{
                Log.d(ContentValues.TAG, "Network has No Connection Capability: ${network} $networkCapabilities")
            }
            postValue(isInternet && isValidated)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            //5
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        //6
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        //7
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}