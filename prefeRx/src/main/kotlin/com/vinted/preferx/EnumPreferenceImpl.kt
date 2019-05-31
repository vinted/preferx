package com.vinted.preferx

import android.content.SharedPreferences

class EnumPreferenceImpl<T : Enum<T>>(
        preferences: SharedPreferences,
        key: String,
        default: T
) : BasePreferenceImpl<T>(
        preferences,
        key,
        default,
        EnumSerializer<T>()
), EnumPreference<T>