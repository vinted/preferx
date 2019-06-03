package com.vinted.preferx

import android.annotation.SuppressLint
import android.content.SharedPreferences
import io.reactivex.Observable

abstract class BasePreferenceImpl<T> internal constructor(
        private val preferences: SharedPreferences,
        private val key: String,
        private val defaultValue: T,
        private val serializer: Serializer<T>
) : BasePreference<T> {

    @SuppressLint("ApplySharedPref")
    override fun set(value: T, commit: Boolean) {
        val editor = preferences.edit().apply {
            serializer.serialize(this, key, value)
        }

        if (commit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    override fun get(): T {
        return serializer.deserialize(preferences, key, defaultValue)
    }

    override fun delete() {
        preferences.edit().remove(key).apply()
    }

    override fun isSet() = preferences.contains(key)

    override val onChangeObservable: Observable<T> by lazy {
        Observable.create<T> { subscriber ->
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
                if (key == this.key) {
                    val value = serializer.deserialize(prefs, key, defaultValue)
                    subscriber.onNext(value)
                }
            }
            preferences.registerOnSharedPreferenceChangeListener(listener)
            subscriber.setCancellable { preferences.unregisterOnSharedPreferenceChangeListener(listener) }
        }
    }
}
