package com.kiwilss.lutils.ktx

import android.content.BroadcastReceiver
import android.content.Context

import android.content.IntentFilter
import androidx.fragment.app.Fragment
import com.kiwilss.lutils.help.jump.createBroadcastIntent


/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/7/6
 * @desc   : {DESCRIPTION}
 */
object BroadcastKtx {
    /**
     * 注册广播
     *
     * @param context
     * @param broadcastReceiver
     * @param action
     */
    fun registerBroadcast(
        context: Context?,
        broadcastReceiver: BroadcastReceiver?,
        vararg action: String?
    ) {
        if (context == null || broadcastReceiver == null) return
        val intentFilter = IntentFilter()
        for (element in action) {
            intentFilter.addAction(element)
        }
        context.registerReceiver(broadcastReceiver, intentFilter)
    }

    /**
     * 解除注册广播，广播要和注册时是同一个
     *
     * @param context
     * @param broadcastReceiver
     */
    fun unregisterBroadcast(context: Context?, broadcastReceiver: BroadcastReceiver?) {
        if (context == null || broadcastReceiver == null) return
        context.unregisterReceiver(broadcastReceiver)
    }

    /**
     *发送广播
     * @param context
     * @param action
     * @param pair
     */
    fun sendBroadcast(context: Context?, action: String?, vararg pair: Pair<String, Any?>) {
        if (context == null || action.isNullOrEmpty()) return
        context.sendBroadcast(context.createBroadcastIntent(action, *pair))
    }

}

/**
 * 注册广播
 *
 * @param broadcastReceiver
 * @param action
 */
fun Context?.registerBroadcast(
    broadcastReceiver: BroadcastReceiver?,
    vararg action: String?
) = BroadcastKtx.registerBroadcast(this, broadcastReceiver, *action)

/**
 * 解除注册广播，广播要和注册时是同一个
 *
 * @param broadcastReceiver
 */
fun Context?.unregisterBroadcast(broadcastReceiver: BroadcastReceiver?) =
    BroadcastKtx.unregisterBroadcast(this, broadcastReceiver)


/**
 * 注册广播
 *
 * @param broadcastReceiver
 * @param action
 */
fun Fragment?.registerBroadcast(
    broadcastReceiver: BroadcastReceiver?,
    vararg action: String?
) = BroadcastKtx.registerBroadcast(this?.context, broadcastReceiver, *action)

/**
 * 解除注册广播，广播要和注册时是同一个
 *
 * @param broadcastReceiver
 */
fun Fragment?.unregisterBroadcast(broadcastReceiver: BroadcastReceiver?) =
    BroadcastKtx.unregisterBroadcast(this?.context, broadcastReceiver)

/**
 *发送广播
 * @param action
 * @param pair
 */
fun Context?.sendBroadcast(action: String?, vararg pair: Pair<String, Any?>) =
    BroadcastKtx.sendBroadcast(this, action, *pair)

/**
 *发送广播
 * @param action
 * @param pair
 */
fun Fragment?.sendBroadcast(action: String?, vararg pair: Pair<String, Any?>) =
    BroadcastKtx.sendBroadcast(this?.context, action, *pair)