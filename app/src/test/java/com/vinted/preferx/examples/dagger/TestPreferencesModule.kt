package com.vinted.android.preferences.dagger

import com.nhaarman.mockito_kotlin.mock
import com.vinted.preferx.examples.AppPreferences
import com.vinted.preferx.IntPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestPreferencesModule {

    @Singleton
    @Provides
    fun provideAppPreferences(): AppPreferences {
        return object : AppPreferences {
            override val appStartCounter: IntPreference = mock()
        }
    }
}