package com.vinted.preferx.examples

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dagger.*
import javax.inject.Inject

class DaggerActivityExample : Activity() {

    @Inject
    lateinit var preferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as PrefApplication).component.inject(this)
        setContentView(R.layout.activity_dagger)

        val launchCount = preferences.appStartCounter.get() + 1
        preferences.appStartCounter.set(launchCount)

        counter.text = getString(R.string.launch_count, launchCount)
    }
}
