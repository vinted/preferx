package com.vinted.preferx

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EnumPreferenceImplTest {


    private val appContext = ApplicationProvider.getApplicationContext<Context>()
    private val sharedPref = appContext.getSharedPreferences("TestPrefs", Context.MODE_PRIVATE)
    private val sharedPrefEditor: SharedPreferences.Editor = sharedPref.edit()
    private val fixture = EnumPreferenceImpl(sharedPref, "key", TestEnum.BAR)

    @Test
    fun beforeEach() {
        sharedPrefEditor.clear()
    }

    @Test
    fun saveValue_returnSame() {
        fixture.set(TestEnum.FOO)

        val value = fixture.get()

        assertEquals(TestEnum.FOO, value)
    }

    @Test
    fun noSavedValue_returnDefault() {
        val value = fixture.get()

        assertEquals(TestEnum.BAR, value)
    }

    enum class TestEnum {
        FOO, BAR
    }
}
