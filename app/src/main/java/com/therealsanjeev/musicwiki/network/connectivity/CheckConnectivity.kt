package com.therealsanjeev.musicwiki.network.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


class CheckConnectivity : BroadcastReceiver() {
    override fun onReceive(context: Context?, arg1: Intent) {
        val isConnected = arg1.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
        if (isConnected) {
            Toast.makeText(context, "Internet Connection Lost", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show()
        }
    }
}