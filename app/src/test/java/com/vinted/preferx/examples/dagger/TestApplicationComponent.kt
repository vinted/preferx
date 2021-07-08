package com.vinted.preferx.examples.dagger

import com.vinted.preferx.examples.DaggerActivityExampleTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestPreferencesModule::class])
interface TestApplicationComponent : ApplicationComponent {

    fun inject(test: DaggerActivityExampleTest)
}