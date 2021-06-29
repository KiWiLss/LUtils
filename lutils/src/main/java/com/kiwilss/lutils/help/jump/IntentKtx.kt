package com.kiwilss.lutils.help.jump

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

object IntentKtx {
    fun addPair(intent: Intent, vararg params: Pair<String, Any?>) {
        params.forEach {
            when (val value = it.second) {
                null -> intent.putExtra(it.first, null as Serializable?)
                is Int -> intent.putExtra(it.first, value)
                is Long -> intent.putExtra(it.first, value)
                is CharSequence -> intent.putExtra(it.first, value)
                is String -> intent.putExtra(it.first, value)
                is Float -> intent.putExtra(it.first, value)
                is Double -> intent.putExtra(it.first, value)
                is Char -> intent.putExtra(it.first, value)
                is Short -> intent.putExtra(it.first, value)
                is Boolean -> intent.putExtra(it.first, value)
                is Serializable -> intent.putExtra(it.first, value)
                is Bundle -> intent.putExtra(it.first, value)
                is Parcelable -> intent.putExtra(it.first, value)
                is Array<*> -> when {
                    value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                    else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                }
                is IntArray -> intent.putExtra(it.first, value)
                is LongArray -> intent.putExtra(it.first, value)
                is FloatArray -> intent.putExtra(it.first, value)
                is DoubleArray -> intent.putExtra(it.first, value)
                is CharArray -> intent.putExtra(it.first, value)
                is ShortArray -> intent.putExtra(it.first, value)
                is BooleanArray -> intent.putExtra(it.first, value)
                else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
        }
    }

    /**
     * 不带回调跳转相关
     */
    fun startActivityK(context: Context?, clazz: Class<*>) {
        context?.startActivity(Intent(context, clazz))
    }

    inline fun <reified T> startActivityK(context: Context?) {
        context?.startActivity(Intent(context, T::class.java))
    }

    fun startActivityK(context: Context?, intent: Intent) {
        context?.startActivity(intent)
    }

    fun startActivityK(context: Context?, clazz: Class<*>, vararg params: Pair<String, Any?>) {
        context?.run {
            val intent = Intent(this, clazz)
            intent.addPair(*params)
            startActivity(intent)
        }
    }

    inline fun <reified T> startActivityK(context: Context?, vararg params: Pair<String, Any?>) {
        context?.run {
            val intent = Intent(this, T::class.java)
            intent.addPair(*params)
            startActivity(intent)
        }
    }
}

/**
 *生成跳转的Intent并添加参数
 * @param T
 * @param pair
 */
inline fun <reified T> Context.createIntent(vararg pair: Pair<String, Any?>) =
    Intent(this, T::class.java).apply {
        addPair(*pair)
    }

fun Intent?.addPair(vararg params: Pair<String, Any?>): Intent? {
    return this?.also { IntentKtx.addPair(it, *params) }
}

/**
 * 不带回调,无参跳转
 */
fun Context?.startActivityK(clazz: Class<*>) {
    this?.startActivity(Intent(this, clazz))
}

inline fun <reified T> Context?.startActivityK() {
    this?.startActivity(Intent(this, T::class.java))
}

/**
 * 不带回调,带参跳转
 */
fun Context?.startActivityK(intent: Intent) {
    this?.startActivity(intent)
}

fun Context?.startActivityK(clazz: Class<*>, vararg pair: Pair<String, Any?>) {
    this?.run {
        val intent = Intent(this, clazz)
        startActivity(intent.addPair(*pair))
    }
}

inline fun <reified T> Context?.startActivityK(vararg pair: Pair<String, Any?>) {
    this?.run {
        val intent = Intent(this, T::class.java)
        startActivity(intent.addPair(*pair))
    }
}

/**
 * 不带回调,无参跳转
 */
fun Fragment?.startActivityK(clazz: Class<*>) {
    this?.startActivity(Intent(activity, clazz))
}

inline fun <reified T> Fragment?.startActivityK() {
    this?.startActivity(Intent(activity, T::class.java))
}

/**
 * 不带回调,带参跳转
 */
fun Fragment?.startActivityK(intent: Intent) {
    this?.startActivity(intent)
}

fun Fragment?.startActivityK(clazz: Class<*>, vararg pair: Pair<String, Any?>) {
    this?.run {
        val intent = Intent(activity, clazz)
        startActivity(intent.addPair(*pair))
    }
}

inline fun <reified T> Fragment?.startActivityK(vararg pair: Pair<String, Any?>) {
    this?.run {
        val intent = Intent(activity, T::class.java)
        startActivity(intent.addPair(*pair))
    }
}


/**
 * Context扩展函数跳转
 */
fun Context?.startActivityForResultK(clazz: Class<*>, callback: ((Int, Intent?) -> Unit)?) {
    ActivityHelper.init(this)?.startActivityForResult(clazz, callback)
}

fun Context?.startActivityForResultK(intent: Intent, callback: ((Int, Intent?) -> Unit)?) {
    ActivityHelper.init(this)?.startActivityForResult(intent, callback)
}

inline fun <reified T> Context?.startActivityForResultK(noinline callback: ((Int, Intent?) -> Unit)?) {
    ActivityHelper.init(this)?.startActivityForResult<T>(callback)

}

fun Context?.startActivityForResultK(
    clazz: Class<*>,
    vararg pair: Pair<String, Any?>,
    callback: ((Int, Intent?) -> Unit)?
) {
    ActivityHelper.init(this)
        ?.startActivityForResult(clazz, callback, *pair)
}

inline fun <reified T> Context?.startActivityForResultK(
    vararg pair: Pair<String, Any?>,
    noinline callback: ((Int, Intent?) -> Unit)?
) {
    ActivityHelper.init(this)
        ?.startActivityForResult<T>(callback, *pair)
}


/**
 * 上述方法在 fragment 中扩展
 */
fun Fragment?.startActivityForResultK(clazz: Class<*>, callback: ((Int, Intent?) -> Unit)?) {
    ActivityHelper.init(this?.context)?.startActivityForResult(clazz, callback)
}

fun Fragment?.startActivityForResultK(intent: Intent, callback: ((Int, Intent?) -> Unit)?) {
    ActivityHelper.init(this?.context)?.startActivityForResult(intent, callback)
}

inline fun <reified T> Fragment?.startActivityForResultK(noinline callback: ((Int, Intent?) -> Unit)?) {
    ActivityHelper.init(this?.context)?.startActivityForResult<T>(callback)
}

fun Fragment?.startActivityForResultK(
    clazz: Class<*>,
    vararg pair: Pair<String, Any?>,
    callback: ((Int, Intent?) -> Unit)?
) {
    ActivityHelper.init(this?.context)
        ?.startActivityForResult(clazz, callback, *pair)
}

inline fun <reified T> Fragment?.startActivityForResultK(
    vararg pair: Pair<String, Any?>,
    noinline callback: ((Int, Intent?) -> Unit)?
) {
    ActivityHelper.init(this?.context)
        ?.startActivityForResult<T>(callback, *pair)
}