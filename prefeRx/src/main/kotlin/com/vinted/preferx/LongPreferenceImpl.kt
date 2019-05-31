package com.vinted.preferx

import android.content.SharedPreferences

class LongPreferenceImpl(
        preferences: SharedPreferences,
        key: String,
        default: Long
) : BasePreferenceImpl<Long>(
        preferences,
        key,
        default,
        IntSerializer
), LongPreference {

    private object IntSerializer : Serializer<Long> {
        override fun serialize(storage: SharedPreferences.Editor, key: String, value: Long) {
            storage.putLong(key, value)
        }

        override fun deserialize(source: SharedPreferences, key: String, default: Long): Long {
            return source.getLong(key, default)
        }
    }
}
