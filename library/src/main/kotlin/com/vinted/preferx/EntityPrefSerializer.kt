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

        if (cachedValue != null && cachedValue.string == string) return cachedValue.value
        if (string.isEmpty()) return default

        val value = serializer.fromString(string, clazz)!!
        cached = SoftReference(Cache(value as T, string))
        return value
    }

    private class Cache<out T>(val value: T, val string: String)
}