package com.vinted.preferx.examples

import com.vinted.android.preferences.dagger.DaggerTestApplicationComponent
import com.vinted.preferx.examples.dagger.TestApplicationComponent
import com.vinted.android.preferences.dagger.TestPreferencesModule

class TestPrefApplication : PrefApplication() {

    val testComponent: TestApplicationComponent
        get() = component as TestApplicationComponent

    override fun buildComponent() {
        component = DaggerTestApplicationComponent
                .builder()
                .testPreferencesModule(TestPreferencesModule())
                .build()
    }
}