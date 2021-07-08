package com.vinted.preferx.examples.dagger

import com.vinted.preferx.IntPreference
import com.vinted.preferx.examples.AppPreferences
import dagger.Module
import dagger.Provides
import org.mockito.kotlin.mock
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