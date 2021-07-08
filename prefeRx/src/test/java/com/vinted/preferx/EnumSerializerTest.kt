package com.vinted.preferx

import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class EnumSerializerTest {

    private val sharedPref: SharedPreferences = mock()
    private val sharedPrefEditor: SharedPreferences.Editor = mock()
    private val fixture = EnumSerializer<TestEnum>()

    @Test
    fun serialize() {
        fixture.serialize(sharedPrefEditor, "key", TestEnum.BAR)

        verify(sharedPrefEditor).putInt("key", TestEnum.BAR.ordinal)
    }

    @Test
    fun deserialize_noValue_defaultValue() {
        val value = fixture.deserialize(sharedPref, "key", TestEnum.FOO)

        assertEquals(TestEnum.FOO, value)
    }

    @Test
    fun deserialize_withSavedValue_savedValue() {
        `when`(sharedPref.getInt(eq("key"), anyInt())).thenReturn(TestEnum.BAR.ordinal)

        val value = fixture.deserialize(sharedPref, "key", TestEnum.FOO)

        assertEquals(TestEnum.BAR, value)
    }

    @Test
    fun deserialize_invalidOrdinal_defaultValue() {
        `when`(sharedPref.getInt(eq("key"), anyInt())).thenReturn(Int.MAX_VALUE)

        val value = fixture.deserialize(sharedPref, "key", TestEnum.FOO)

        assertEquals(TestEnum.FOO, value)
    }


    enum class TestEnum {
        FOO, BAR
    }
}
