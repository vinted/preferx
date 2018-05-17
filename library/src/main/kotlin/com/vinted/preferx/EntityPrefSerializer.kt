package com.vinted.preferx

import android.content.SharedPreferences
import java.lang.ref.SoftReference

class EntityPrefSerializer<T : Any>(
        private val serializer: PreferxSerializer,
        private val clazz: Class<T>
) : Serializer<T> {

    private var cached: SoftReference<Cache<T>>? = null

    override fun serialize(storage: SharedPreferences.Editor, key: String, value: T) {
        val string = serializer.toString(value)
        cached = SoftReference(Cache(value, string))
        storage.putString(key, string)
    }

    override fun deserialize(source: SharedPreferences, key: String, default: T): T {
        val string = source.getString(key, "") ?: ""

        val cachedValue = cached?.get()

        return if (cachedValue != null && cachedValue.string == string) {
            cachedValue.value
        } else {
            val value = serializer.fromString(string, clazz) as? T

            if (value != null) {
                cached = SoftReference(Cache(value, string))
            }

            value ?: default
        }
    }

    private class Cache<out T>(val value: T, val string: String)
}