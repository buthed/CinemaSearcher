package com.tematikhonov.cinemasearcher.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context.let{
            Toast.makeText(context,"MyBroadcastReceiver", Toast.LENGTH_SHORT).show()
        }
    }
}