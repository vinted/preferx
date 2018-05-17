package com.vinted.preferx.examples

import android.app.Application
import com.vinted.preferx.examples.dagger.ApplicationComponent
import com.vinted.preferx.examples.dagger.DaggerApplicationComponent
import com.vinted.preferx.examples.dagger.PreferencesModule

open class PrefApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        buildComponent()
    }

    protected open fun buildComponent() {
        component = DaggerApplicationComponent.builder()
                .preferencesModule(PreferencesModule(this))
                .build()
    }
}