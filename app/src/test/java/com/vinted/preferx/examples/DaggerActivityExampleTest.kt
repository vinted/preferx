package com.vinted.preferx.examples

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.android.synthetic.main.activity_dagger.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,
        application = TestPrefApplication::class)
class DaggerActivityExampleTest {

    @Inject
    lateinit var preferences: AppPreferences

    @Before
    fun setUp() {
        (RuntimeEnvironment.application as TestPrefApplication).testComponent.inject(this)

        whenever(preferences.appStartCounter.get()).doReturn(1)
    }

    @Test
    fun verify_textView_updated() {
        val activity = Robolectric.setupActivity(DaggerActivityExample::class.java)

        val expectedText = activity.getString(R.string.launch_count, 2)
        assertEquals(expectedText, activity.counter.text)
    }
}