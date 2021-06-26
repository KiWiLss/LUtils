package com.kiwilss.lutils.ktx

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * 任意对象转成json
 */
fun Any?.toJson() = GsonKtx.toJson(this)
fun Any?.toJson(gson: Gson) = GsonKtx.toJson(gson, this)

/**
 *将json数据转成任意bean类
 */
inline fun <reified T> String?.toAny() = GsonKtx.toAny<T>(this)
fun <T> String?.toAny(clazz: Class<T>) = GsonKtx.toAny(this, clazz)
fun <T> String?.toAny(gson: Gson, clazz: Class<T>) = GsonKtx.toAny(gson, this, clazz)

/**
 *将json数据转成Map
 */
inline fun <reified T> String?.toMap() = GsonKtx.toMap<T>(this)
fun <T> String?.toMap(clz: Class<T>?) = GsonKtx.toMap(this, clz)

/**
 *将json数据转成任意集合bean类
 */
fun <T> String?.toList(clz: Class<Array<T>>?) = GsonKtx.toList(this, clz)
fun <T> String?.toList(gson: Gson, clz: Class<Array<T>>?) = GsonKtx.toList(gson, this, clz)

/**
 *将json数据转成任意集合bean类
 */
inline fun <reified T> String?.toList2() = GsonKtx.toList2<T>(this)
fun <T> String?.toList2(clz: Class<T>?) = GsonKtx.toList2(this, clz)

/**
 *将json数据转成任意集合bean类，需要自定义Type
 */
fun <T> String?.toListType(type: Type) = GsonKtx.toListType<T>(this, type)

/**
 *将json数据转成任意集合Map类
 */
inline fun <reified T> String?.toListMap() = GsonKtx.toListMap<T>(this)
fun <T> String?.toListMap(clazz: Class<T>) = GsonKtx.toListMap(this, clazz)


object GsonKtx {

    private val gson = Gson()

    /**
     *获取 gson
     */
    fun getGson() = gson

    /**
     * 任意对象转成json
     */
    fun toJson(any: Any?): String? {
        return toJson(gson, any)
    }

    fun toJson(gson: Gson, any: Any?): String? {
        return try {
            gson.toJson(any)
        } catch (e: Exception) {
            null
        }
    }

    /**
     *将json数据转成任意bean类
     */
    inline fun <reified T> toAny(json: String?): T? {
        return toAny(json, T::class.java)
    }

    fun <T> toAny(json: String?, clazz: Class<T>): T? {
        return toAny(gson, json, clazz)
    }

    fun <T> toAny(gson: Gson, json: String?, clazz: Class<T>): T? {
        return try {
            gson.fromJson(json, clazz)
        } catch (e: Exception) {
            null
        }
    }

    /**
     *将json数据转成任意集合bean类
     */
    fun <T> toList(json: String?, clz: Class<Array<T>>?): List<T>? {
        return toList(gson, json, clz)
    }

    fun <T> toList(gson: Gson, json: String?, clz: Class<Array<T>>?): List<T>? {
        return try {
            val ts: Array<T> = gson.fromJson(json, clz)
            mutableListOf(*ts)
        } catch (e: Exception) {
            null
        }
    }

    /**
     *将json数据转成任意集合bean类，需要自己定义好Type
     *   val typeToken: TypeToken<List<TestBean>> = object : TypeToken<List<TestBean>>() {}
     */
    fun <T> toListType(json: String?, type: Type): List<T>? {
        return toListType(gson, json, type)
    }

    fun <T> toListType(gson: Gson, json: String?, type: Type): List<T>? {
        return try {
            gson.fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Json转List集合,遇到解析不了的，就使用这个
     */
    inline fun <reified T> toList2(json: String?): List<T> {
        return toList2(json, T::class.java)
    }

    fun <T> toList2(json: String?, cls: Class<T>?): List<T> {
        val mList: MutableList<T> = ArrayList()
        try {
            val array = JsonParser.parseString(json).asJsonArray
//            val array = JsonParser().parse(json).asJsonArray
            for (elem in array) {
                mList.add(gson.fromJson(elem, cls))
            }
        } catch (e: Exception) {
        }
        return mList
    }

    /**
     * Json转换成Map的List集合对象
     */
    inline fun <reified T> toListMap(json: String?): List<Map<String?, T>?>? {
        return toListMap(json, T::class.java)
    }

    fun <T> toListMap(json: String?, clz: Class<T>?): List<Map<String?, T>?>? {
        return toListMap(gson, json, clz)
    }

    fun <T> toListMap(gson: Gson, json: String?, clz: Class<T>?): List<Map<String?, T>?>? {
        return try {
            val type: Type = object : TypeToken<List<Map<String?, T>?>?>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Json转Map对象
     */
    inline fun <reified T> toMap(json: String?): Map<String?, T>? {
        return toMap(json, T::class.java)
    }

    fun <T> toMap(json: String?, clz: Class<T>?): Map<String?, T>? {
        return toMap(gson, json, clz)
    }

    fun <T> toMap(gson: Gson, json: String?, clazz: Class<T>?): Map<String?, T>? {
        return try {
            val type = object : TypeToken<Map<String?, T>?>() {}.type
            return gson.fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }


}