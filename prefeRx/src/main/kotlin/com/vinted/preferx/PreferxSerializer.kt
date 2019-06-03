package com.vinted.preferx

import java.lang.reflect.Type

interface PreferxSerializer<T: Any> {
    fun toString(value: T, type: Type): String
    fun fromString(string: String, type: Type): T
}
