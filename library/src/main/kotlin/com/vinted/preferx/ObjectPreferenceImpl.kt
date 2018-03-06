package com.vinted.preferx

import android.content.SharedPreferences

class ObjectPreferenceImpl<T : Any>(
        prefs: SharedPreferences,
        key: String,
        default: T,
        serializer: EntitySerializer<T>,
        clazz: Class<T>
) : BasePreferenceImpl<T>(
        prefs,
        key,
        default,
        EntityPrefSerializer(serializer, clazz)
), ObjectPreference<T>