package com.vinted.preferx

import java.lang.reflect.Type

interface PreferxSerializer {
    fun toString(value: Any): String
    fun fromString(string: String, type: Type): Any?
}
