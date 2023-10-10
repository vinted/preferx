package com.vinted.preferx

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EnumPreferenceImplTest {

    @Test
    fun saveValue_returnSame() {
        val fixture = createTestEnumPreference()

        fixture.set(TestEnum.FOO)

        val value = fixture.get()

        assertEquals(TestEnum.FOO, value)
    }

    @Test
    fun noSavedValue_returnDefault() {
        val fixture = createTestEnumPreference()

        val value = fixture.get()

        assertEquals(TestEnum.BAR, value)
    }

    private fun createTestEnumPreference(default: TestEnum = TestEnum.BAR): EnumPreference<TestEnum> {
        val sharedPreferences = ApplicationProvider
            .getApplicationContext<Context>()
            .getSharedPreferences("TestPrefs", Context.MODE_PRIVATE)

        sharedPreferences.edit().clear()

        return EnumPreferenceImpl(sharedPreferences, "key", default)
    }

    enum class TestEnum {
        FOO, BAR
    }
}
