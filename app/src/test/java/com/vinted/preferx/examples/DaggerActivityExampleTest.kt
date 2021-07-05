package com.vinted.preferx.examples

import kotlinx.android.synthetic.main.activity_dagger.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(
    application = TestPrefApplication::class
)
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