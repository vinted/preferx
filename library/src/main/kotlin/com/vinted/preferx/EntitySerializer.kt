package com.vinted.preferx

import java.lang.reflect.Type

interface EntitySerializer<T> {
    fun toString(value: T): String
    fun fromString(string: String, type: Type): T
}