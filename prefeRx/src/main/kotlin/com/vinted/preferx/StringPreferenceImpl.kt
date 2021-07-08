package com.vinted.preferx

import android.content.SharedPreferences

class StringPreferenceImpl(
        preferences: SharedPreferences,
        key: String,
        default: String
) : BasePreferenceImpl<String>(
        preferences,
        key,
        default,
        StringSerializer
), StringPreference {

    private object StringSerializer : Serializer<String> {
        override fun serialize(storage: SharedPreferences.Editor, key: String, value: String) {
            storage.putString(key, value)
        }

        override fun deserialize(source: SharedPreferences, key: String, default: String): String {
            return source.getString(key, default)!!
        }
    }
}
