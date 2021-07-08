package com.kiwilss.lutils.ktx

import android.content.BroadcastReceiver
import android.content.Context

import android.content.IntentFilter
import androidx.fragment.app.Fragment


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
