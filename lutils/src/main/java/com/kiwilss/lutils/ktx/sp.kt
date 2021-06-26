package com.kiwilss.lutils.ktx

import android.content.Context
import android.content.SharedPreferences
import com.kiwilss.lutils.LUtilsConfig

fun Any.sp(name: String = LUtilsConfig.SP_DEFAULT_NAME) =
    LUtilsConfig.getContext().getSharedPreferences(name, Context.MODE_PRIVATE)

/**

批处理
 */
fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    edit().apply { action() }.apply()
}

/**

对象操作
 */
fun SharedPreferences.putAny(key: String, obj: Any?) {
    putString(key, obj?.toJson() ?: "")
}

/**
 *获取任意对象
 */
fun <T> SharedPreferences.getAny(key: String, clazz: Class<T>): T? {
    val string = getString(key, null)
    if (string.isNullOrEmpty()) return null
    return string.toAny(clazz)
}

inline fun <reified T> SharedPreferences.getAny(key: String): T? {
    val string = getString(key, null)
    if (string.isNullOrEmpty()) return null
    return string.toAny()
}

/**
 *获取list
 */
fun <T> SharedPreferences.getList(key: String, clazz: Class<T>): List<T>? {
    val string = getString(key, null)
    if (string.isNullOrEmpty()) return null
    return string.toList2(clazz)
}

inline fun <reified T> SharedPreferences.getList(key: String): List<T>? {
    val string = getString(key, null)
    if (string.isNullOrEmpty()) return null
    return string.toList2()
}

/**

put系列
 */
fun SharedPreferences.putString(key: String, value: String?) {
    edit { putString(key, value ?: "") }
}

fun SharedPreferences.putInt(key: String, value: Int) {
    edit { putInt(key, value) }
}

fun SharedPreferences.putBoolean(key: String, value: Boolean) {
    edit { putBoolean(key, value) }
}

fun SharedPreferences.putFloat(key: String, value: Float) {
    edit { putFloat(key, value) }
}

fun SharedPreferences.putLong(key: String, value: Long) {
    edit { putLong(key, value) }
}

fun SharedPreferences.putStringSet(key: String, value: Set<String>) {
    edit { putStringSet(key, value) }
}

fun SharedPreferences.clear(key: String) {
    edit { remove(key) }
}

fun SharedPreferences.clear() {
    edit { clear() }
}

