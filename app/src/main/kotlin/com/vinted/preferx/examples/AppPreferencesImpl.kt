package com.vinted.preferx.examples

import android.content.SharedPreferences
import com.vinted.preferx.intPreference

class AppPreferencesImpl(
        private val sharedPreferences: SharedPreferences
) : AppPreferences {

    override val appStartCounter by lazy {
        sharedPreferences.intPreference("app-start-counter", 0)
    }
}