package com.vinted.preferx

import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.*
import com.vinted.preferx.BasePreferenceImpl
import com.vinted.preferx.Serializer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BasePreferenceImplTest {

    private val serializer = mock<Serializer<Int>>()
    private val editor = mock<SharedPreferences.Editor>()
    private val prefs = mock<SharedPreferences> {
        on { edit() } doReturn editor
    }
    private val key = "foo"
    private val default = 123
    private val fixture = object : BasePreferenceImpl<Int>(prefs, key, default, serializer) {}

    @Before
    fun setUp() {
        whenever(editor.remove(any())).thenReturn(editor)
    }

    @Test
    fun testSet_serializeAndApply() {
        fixture.set(9)

        verify(serializer).serialize(editor, key, 9)
        verify(editor).apply()
    }

    @Test
    fun testSetSync_serializeAndCommit() {
        fixture.set(9, commit = true)

        verify(serializer).serialize(editor, key, 9)
        verify(editor).commit()
    }

    @Test
    fun testGet_getValueFromSerializer() {
        whenever(serializer.deserialize(any(), any(), any())).thenReturn(3)

        val value = fixture.get()

        assertEquals(3, value)
        verify(serializer).deserialize(prefs, key, default)
    }

    @Test
    fun testDelete_callEditorDeleteAndApply() {
        fixture.delete()

        verify(editor).remove(key)
        verify(editor).apply()
    }

    @Test
    fun testIsSet_isSet_getTrue() {
        whenever(prefs.contains(any())).thenReturn(true)

        val isSet = fixture.isSet()

        assertTrue(isSet)
        verify(prefs).contains(key)
    }

    @Test
    fun testIsSet_isNotSet_getFalse() {
        whenever(prefs.contains(any())).thenReturn(false)

        val isSet = fixture.isSet()

        assertFalse(isSet)
        verify(prefs).contains(key)
    }

}