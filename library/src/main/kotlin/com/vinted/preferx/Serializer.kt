package com.vinted.preferx

import android.content.SharedPreferences

interface Serializer<V> {
    fun serialize(storage: SharedPreferences.Editor, key: String, value: V)
    fun deserialize(source: SharedPreferences, key: String, default: V): V
}
