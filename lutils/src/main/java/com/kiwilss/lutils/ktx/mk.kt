package com.kiwilss.lutils.ktx

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.io.Serializable

/**
 *获取MMKV实例
 * @param id 单独创建实例
 * @param isMultiProcess 是否在多进程中
 * @return
 */
fun Any.mk(id: String? = null, isMultiProcess: Boolean = false): MMKV {
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
 *基本数据类型等常规数据的保存
 * @param pair
 * @return
 */
fun MMKV.encode(vararg pair: Pair<String, Any?>): Boolean {
    var result = false
    pair.forEach {
        when (val value = it.second) {
            null -> result = false
            is Int -> result = encode(it.first, value)
            is Long -> result = encode(it.first, value)
            is CharSequence -> result = encode(it.first, value.toString())
            is String -> result = encode(it.first, value)
            is Float -> result = encode(it.first, value)
            is Double -> result = encode(it.first, value)
            is Char -> result = encode(it.first, value.toString())
            is Boolean -> result = encode(it.first, value)
            is Parcelable -> result = encode(it.first, value)
            is ByteArray -> result = encode(it.first, value)
            is Set<*> -> result = encode(it.first, value as Set<String>)
            is Array<*> -> result = encode(it.first, value.toJson())
            is Serializable -> result = encode(it.first, value.toJson())
        }
    }
    return result
}

fun MMKV.int(key: String) = decodeInt(key)
fun MMKV.long(key: String) = decodeLong(key)
fun MMKV.string(key: String) = decodeString(key)
fun MMKV.float(key: String) = decodeFloat(key)
fun MMKV.double(key: String) = decodeDouble(key)
fun MMKV.bool(key: String) = decodeBool(key)

fun <T : Parcelable> MMKV.parcelable(key: String, clz: Class<T>) = decodeParcelable(key, clz)
inline fun <reified T : Parcelable> MMKV.parcelable(key: String) =
    decodeParcelable(key, T::class.java)

fun MMKV.bytes(key: String) = decodeBytes(key)

inline fun <reified T> MMKV.any(key: String): T? = decodeString(key).toAny()
inline fun <reified T> MMKV.array(key: String): List<T> = decodeString(key).toList2()

fun MMKV.contain(key: String) = containsKey(key)

fun MMKV.remove(key: String) = removeValueForKey(key)
fun MMKV.removes(keys: Array<String>) = removeValuesForKeys(keys)
fun MMKV.clear() = clearAll()
fun MMKV.clearCache() = clearMemoryCache()