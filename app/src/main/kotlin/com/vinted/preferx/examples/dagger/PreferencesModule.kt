package com.vinted.preferx.examples.dagger

import android.content.Context
import android.content.SharedPreferences
import com.vinted.preferx.examples.AppPreferences
import com.vinted.preferx.examples.AppPreferencesImpl
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule(
        private val context: Context
) {

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("app-preferences", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideAppPreferences(
            sharedPreferences: SharedPreferences
    ): AppPreferences {
        return AppPreferencesImpl(sharedPreferences)
    }
}