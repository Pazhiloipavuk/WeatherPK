package com.example.weatherpk.util

import android.content.Context

class Preferences {
    companion object {
        fun getPreferences(context: Context)= context.getSharedPreferences(PREFERENCES,
                                                                           Context.MODE_PRIVATE)!!

        private const val PREFERENCES = "PREFERENCES"
    }
}