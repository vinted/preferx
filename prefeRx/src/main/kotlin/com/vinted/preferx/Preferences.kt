package com.vinted.preferx

import io.reactivex.Observable

interface BasePreference<T> {

    fun get(): T

    fun set(value: T, commit: Boolean = false)

    fun delete()

    fun isSet(): Boolean

    /**
     * emits value on changing value of particular key. Emits default for deleting value
     * Must be unsubscribed
     */
    val onChangeObservable: Observable<T>
}

interface BooleanPreference : BasePreference<Boolean>
interface IntPreference : BasePreference<Int>
interface LongPreference : BasePreference<Long>
interface StringPreference : BasePreference<String>
interface ObjectPreference<T> : BasePreference<T>
interface EnumPreference<T : Enum<T>> : BasePreference<T>