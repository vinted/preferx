package com.vinted.preferx.examples.dagger

import com.vinted.preferx.examples.DaggerActivityExample
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PreferencesModule::class])
interface ApplicationComponent {

    fun inject(activity: DaggerActivityExample)
}