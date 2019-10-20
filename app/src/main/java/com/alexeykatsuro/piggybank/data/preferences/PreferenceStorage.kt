package com.alexeykatsuro.piggybank.data.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Storage for app preferences.
 */
interface PreferenceStorage {
    var currentRate: Float
}

/**
 * [PreferenceStorage] impl backed by [android.content.SharedPreferences].
 */
class SharedPreferenceStorage constructor(context: Context) : PreferenceStorage {

    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)


    override var currentRate by FloatPreference(prefs, PREFS_CURRENT_RATE, 0.0f)

    companion object {
        const val PREFS_NAME = "PiggyBank"
        const val PREFS_CURRENT_RATE = "prefs_current_rate"

    }
}

class BooleanPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Boolean,
    private val commit: Boolean = false
) : ReadWriteProperty<Any, Boolean> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.edit { putBoolean(name, value) }
    }
}

class StringNullabePreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String?,
    private val commit: Boolean = false
) : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit(commit) { putString(name, value) }
    }
}

class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String,
    private val commit: Boolean = false
) : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preferences.getString(name, defaultValue)!!
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preferences.edit(commit) { putString(name, value) }
    }
}

class IntegerPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Int,
    private val commit: Boolean = false
) : ReadWriteProperty<Any, Int> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preferences.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preferences.edit(commit) { putInt(name, value) }
    }
}

class LongPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Long,
    private val commit: Boolean = false
) : ReadWriteProperty<Any, Long> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Long {
        return preferences.getLong(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        preferences.edit(commit) { putLong(name, value) }
    }
}

class FloatPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: Float,
    private val commit: Boolean = false
) : ReadWriteProperty<Any, Float> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Float {
        return preferences.getFloat(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
        preferences.edit(commit) { putFloat(name, value) }
    }
}