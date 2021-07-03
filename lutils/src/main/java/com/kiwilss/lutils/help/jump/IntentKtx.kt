package com.kiwilss.lutils.help.jump

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.core.content.ContextCompat
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
inline fun <reified T> Context.createIntentStart(vararg pair: Pair<String, Any?>) =
    Intent(this, T::class.java).apply {
        addPair(*pair)
    }

/**
 *生成Intent添加参数,也可以不添加
 */
fun Context.createIntent(vararg pair: Pair<String, Any?>) = Intent().addPair(*pair)


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

/**
 *跳转到浏览器
 * @param url
 * @param newTask
 * @return
 */
fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

/**
 *跳转到浏览器
 * @param url
 * @param newTask
 * @return
 */
fun Fragment.browse(url: String, newTask: Boolean = false) = activity?.browse(url, newTask)

/**
 *原生调用分享，只能分享文本
 * @param text
 * @param subject
 * @param title
 * @return
 */
fun Context.share(text: String, subject: String = "", title: String? = null): Boolean {
    return try {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, title))
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

/**
 *发送邮件
 * @param email
 * @param subject
 * @param text
 * @return
 */
@SuppressLint("QueryPermissionsNeeded")
fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    if (subject.isNotEmpty())
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (text.isNotEmpty())
        intent.putExtra(Intent.EXTRA_TEXT, text)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        return true
    }
    return false
}

/**
 *拨打电话,需要权限permission.CALL_PHONE才能拨打
 * @param number
 * @return
 */
@SuppressLint("MissingPermission")
fun Context.makeCallPermission(number: String): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("MMM", ": ${e.message}");
        false
    }
}

fun Fragment.markCallPermission(number: String) = context?.makeCallPermission(number)

/**
 *拨打电话,不需要权限,跳转到拨打电话界面
 * @param number
 * @return
 */
fun Context.makeCall(number: String) {
    val uri = Uri.parse("tel:$number")
    val it = Intent(Intent.ACTION_DIAL, uri)
    startActivity(it)
}

fun Fragment.markCall(number: String) = context?.makeCall(number)

/**
 *发送短信
 * @param number
 * @param text
 * @return
 */
fun Context.sendSMS(number: String, text: String = ""): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$number"))
        intent.putExtra("sms_body", text)
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun Fragment.sendSMS(number: String, text: String = "") = context
