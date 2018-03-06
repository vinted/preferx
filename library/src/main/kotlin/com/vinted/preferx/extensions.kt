package com.vinted.preferx

import android.content.SharedPreferences

fun SharedPreferences.intPreference(name: String, defaultValue: Int): IntPreference {
    return IntPreferenceImpl(this, name, defaultValue)
}

fun SharedPreferences.stringPreference(name: String, defaultValue: String): StringPreference {
    return StringPreferenceImpl(this, name, defaultValue)
}

fun SharedPreferences.booleanPreference(name: String, defaultValue: Boolean): BooleanPreference {
    return BooleanPreferenceImpl(this, name, defaultValue)
}

fun SharedPreferences.longPreference(name: String, defaultValue: Long): LongPreference {
    return LongPreferenceImpl(this, name, defaultValue)
}

fun <T : Any> SharedPreferences.objectPreference(
        name: String,
        defaultValue: T,
        serializer: EntitySerializer<T>,
        clazz: Class<T>
): ObjectPreference<T> {
    return ObjectPreferenceImpl(this, name, defaultValue, serializer, clazz)
}

fun <T : Enum<T>> SharedPreferences.enumPreference(name: String, defaultValue: T): EnumPreference<T> {
    return EnumPreferenceImpl(this, name, defaultValue)
}