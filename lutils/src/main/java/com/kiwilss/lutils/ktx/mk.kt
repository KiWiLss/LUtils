package com.kiwilss.lutils.ktx

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.io.Serializable



/**
 *获取MMKV实例
 * @param id 单独创建实例
 * @param isMultiProcess 是否在多进程中使用
 * @return
 */
fun Any?.mk(id: String? = null, isMultiProcess: Boolean = false): MMKV {
    return if (id.isNullOrEmpty()) {
        MMKV.defaultMMKV()
    } else {
        if (isMultiProcess) {
            MMKV.mmkvWithID(id, MMKV.MULTI_PROCESS_MODE)
        } else {
            MMKV.mmkvWithID(id)
        }
    }
}

/**
 *基本数据类型等常规数据的保存,使用默认的MMKV
 * @param pair
 */
fun Any?.encode(vararg pair: Pair<String, Any?>) {
    val mk = mk()
    mk.encode(*pair)
}

/**
 *基本数据类型等常规数据的保存
 * @param pair
 * @return
 */
fun MMKV.encode(vararg pair: Pair<String, Any?>) {
    pair.forEach {
        when (val value = it.second) {
            null -> removeValueForKey(it.first)
            is Int -> encode(it.first, value)
            is Long -> encode(it.first, value)
            is CharSequence -> encode(it.first, value.toString())
            is String -> encode(it.first, value)
            is Float -> encode(it.first, value)
            is Double -> encode(it.first, value)
            is Char -> encode(it.first, value.toString())
            is Boolean -> encode(it.first, value)
            is Parcelable -> encode(it.first, value)
            is ByteArray -> encode(it.first, value)
            is Set<*> -> encode(it.first, value as Set<String>)
            is Array<*> -> encode(it.first, value.toJson())
            is Serializable -> encode(it.first, value.toJson())
        }
    }
}

/**
 *获取保存的数据，只能识别基本数据类型
 * @param T
 * @param key
 * @return
 */
inline fun <reified T> Any?.decode(key: String): T? = null.decode(key)
inline fun <reified T> MMKV?.decode(key: String): T? {
    val mk = this ?: mk()
    val java = T::class.java
    return when {
        java.isAssignableFrom(Int::class.java) -> mk.int(key) as T
        java.isAssignableFrom(Long::class.java) -> mk.long(key) as T
        java.isAssignableFrom(Float::class.java) -> mk.float(key) as T
        java.isAssignableFrom(Double::class.java) -> mk.double(key) as T
        java.isAssignableFrom(Boolean::class.java) -> mk.bool(key) as T
        java.isAssignableFrom(String::class.java) -> mk.string(key) as T
        else -> {
            null
        }
    }
}

/**
 *获取保存的对象类型数据
 * @param T
 * @param key
 * @return
 */
inline fun <reified T> Any?.decodeAny(key: String): T? = null.decodeAny(key)
inline fun <reified T> MMKV?.decodeAny(key: String): T? {
    val mk = this ?: mk()
    return mk.any(key)
}

/**
 *获取保存的list类型数据
 * @param T
 * @param key
 * @return
 */
inline fun <reified T> Any?.decodeList(key: String): List<T> = null.decodeList(key)
inline fun <reified T> MMKV?.decodeList(key: String): List<T> {
    val mk = this ?: mk()
    return mk.list(key)
}

fun MMKV.int(key: String) = decodeInt(key)
fun MMKV.long(key: String) = decodeLong(key)
fun MMKV.float(key: String) = decodeFloat(key)
fun MMKV.double(key: String) = decodeDouble(key)
fun MMKV.bool(key: String) = decodeBool(key)
fun MMKV.string(key: String) = decodeString(key)

fun <T : Parcelable> MMKV.parcelable(key: String, clz: Class<T>) = decodeParcelable(key, clz)
inline fun <reified T : Parcelable> MMKV.parcelable(key: String) =
    decodeParcelable(key, T::class.java)

fun MMKV.bytes(key: String) = decodeBytes(key)

inline fun <reified T> MMKV.any(key: String): T? = decodeString(key).toAny()
inline fun <reified T> MMKV.list(key: String): List<T> = decodeString(key).toList2()


fun MMKV.contain(key: String) = containsKey(key)
fun MMKV.remove(key: String) = removeValueForKey(key)
fun MMKV.removes(keys: Array<String>) = removeValuesForKeys(keys)
fun MMKV.clear() = clearAll()
fun MMKV.clearCache() = clearMemoryCache()

