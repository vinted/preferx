package com.vinted.preferx

import android.content.SharedPreferences
import java.lang.ref.SoftReference

class EntityPrefSerializer<T>(
        private val entitySerializer: EntitySerializer<T>,
        private val clazz: Class<T>
) : Serializer<T> {

    private var cached: SoftReference<Cache<T>>? = null

    override fun serialize(storage: SharedPreferences.Editor, key: String, value: T) {
        val string = entitySerializer.toString(value)
        cached = SoftReference(Cache(value, string))
        storage.putString(key, string)
    }

    override fun deserialize(source: SharedPreferences, key: String, default: T): T {
        val string = source.getString(key, "") ?: ""

        val cachedValue = cached?.get()

        return if (cachedValue != null && cachedValue.string == string) {
            cachedValue.value ?: default
        } else {
            val value = entitySerializer.fromString(string, clazz)
            cached = SoftReference(Cache(value, string))
            value ?: default
        }
    }

    private class Cache<out T>(val value: T, val string: String)
}