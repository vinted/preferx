package com.vinted.preferx

import android.content.SharedPreferences

class EnumSerializer<T : Enum<T>> : Serializer<T> {

    override fun serialize(storage: SharedPreferences.Editor, key: String, value: T) {
        storage.putInt(key, value.ordinal)
    }

    override fun deserialize(source: SharedPreferences, key: String, default: T): T {
        val ordinal = source.getInt(key, default.ordinal)

        return try {
            default.javaClass.enumConstants?.get(ordinal) ?: default
        } catch (e: Throwable) {
            default
        }
    }
}