package com.vinted.preferx

import android.content.SharedPreferences

open class IntPreferenceImpl(
        preferences: SharedPreferences,
        key: String,
        default: Int
) : BasePreferenceImpl<Int>(
        preferences,
        key,
        default,
        IntSerializer
), IntPreference {

    private object IntSerializer : Serializer<Int> {
        override fun serialize(storage: SharedPreferences.Editor, key: String, value: Int) {
            storage.putInt(key, value)
        }

        override fun deserialize(source: SharedPreferences, key: String, default: Int): Int {
            return source.getInt(key, default)
        }
    }
}
