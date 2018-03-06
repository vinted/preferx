package com.vinted.preferx

import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EntityPrefSerializerTest {

    private val prefs: SharedPreferences = mock()
    private val editor: SharedPreferences.Editor = mock()
    private val serializer = mock<EntitySerializer<Int>>()
    private val value = 1
    private val key = "foo"

    private val fixture = EntityPrefSerializer(serializer, Int::class.java)

    @Before
    fun setUp() {
        whenever(serializer.toString(any()))
                .then {
                    it.arguments[0].toString()
                }

        whenever(serializer.fromString(any(), any()))
                .then {
                    it.arguments[0].toString().toIntOrNull()
                }
    }

    @Test
    fun testSerialize_putValueFrom() {
        fixture.serialize(editor, key, value)

        verify(editor).putString(key, "1")
    }

    @Test
    fun testDeserialize_DeserializeValueFrom() {
        whenever(prefs.getString(any(), any())).thenReturn("1")

        val value = fixture.deserialize(prefs, key, 123)

        assertEquals(1, value)
    }

    @Test
    fun testDeserialize_twoDifferentSavedValues_differentResult() {
        whenever(prefs.getString(any(), any())).thenReturn("1")
        val value1 = fixture.deserialize(prefs, key, 123)

        whenever(prefs.getString(any(), any())).thenReturn("2")
        val value2 = fixture.deserialize(prefs, key, 123)

        assertEquals(1, value1)
        assertEquals(2, value2)
    }

    @Test
    fun testDeserialize_sameSavedValue_worksOneTime() {
        whenever(prefs.getString(any(), any())).thenReturn("1")

        fixture.deserialize(prefs, key, 123)
        fixture.deserialize(prefs, key, 123)

        verify(serializer).fromString(any(), any())
    }

    @Test
    fun testDeserialize_serializeValue_useFromCache() {
        whenever(prefs.getString(any(), any())).thenReturn("1")

        fixture.serialize(editor, key, value)

        val value = fixture.deserialize(prefs, key, 123)

        assertEquals(this.value, value)
        verify(serializer, never()).fromString(any(), any())
    }

    @Test
    fun testDeserialize_emptyPref_getDefault() {
        val value = fixture.deserialize(prefs, key, 123)

        assertEquals(123, value)
    }

    @Test
    fun testDeserialize_cacheNull_returnDefault() {
        fixture.deserialize(prefs, key, 123)

        val value = fixture.deserialize(prefs, key, 234)

        assertEquals(234, value)
    }
}