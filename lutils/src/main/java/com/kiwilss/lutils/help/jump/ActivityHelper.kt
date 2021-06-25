package com.kiwilss.lutils.help.jump

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity

class ActivityHelper private constructor(activity: FragmentActivity) {
    private val TAG = "MMMK"
    var mContext: Activity? = null
    private var mRouterFragment: RouterFragment? = null

    init {
        mContext = activity
        mRouterFragment = getRouterFragment(activity)
    }

    companion object {
//        fun init(activity: FragmentActivity?): ActivityHelper? {
//            if (activity != null) return ActivityHelper(activity)
//            return null
//        }

        fun init(context: Context?): ActivityHelper? {
            if (context is FragmentActivity) return ActivityHelper(context)
            return null
        }
    }


    private fun getRouterFragment(activity: FragmentActivity): RouterFragment? {
        var routerFragment: RouterFragment? = findRouterFragment(activity)
        if (routerFragment == null) {
            //创建 fragment,加入当前 activity
            routerFragment = RouterFragment.newInstance()
            val sfm = activity.supportFragmentManager
            sfm.beginTransaction().add(routerFragment!!, TAG).commitAllowingStateLoss()
            sfm.executePendingTransactions()
        }
        return routerFragment
    }

    private fun findRouterFragment(activity: FragmentActivity): RouterFragment? {
        //通过 tag 获取 fragment
        return activity.supportFragmentManager.findFragmentByTag(TAG) as RouterFragment?
    }


    /**
     * 对Intent跳转,不带参数
     */
    fun startActivityForResult(
        clazz: Class<*>,
        callback: ((Int, Intent?) -> Unit)?
    ) {
        mContext?.run {
            val intent = Intent(this, clazz)
            startActivityForResult(intent, callback)
        }
    }

    inline fun <reified T> startActivityForResult(noinline callback: ((Int, Intent?) -> Unit)?) {
        mContext?.run {
            val intent = Intent(mContext, T::class.java)
            startActivityForResult(intent, callback)
        }
    }

    /**
     *带参数跳转
     * @param T
     * @param pair
     * @param callback
     */
    inline fun <reified T> startActivityForResult(
        vararg pair: Pair<String, Any?>,
        noinline callback: ((Int, Intent?) -> Unit)?
    ) {
        if (mContext == null) return
        val intent = Intent(mContext, T::class.java)
        IntentKtx.addPair(intent, *pair)
        startActivityForResult(intent, callback)
    }

    inline fun <reified T> startActivityForResult(
        noinline callback: ((Int, Intent?) -> Unit)?,
        vararg pair: Pair<String, Any?>
    ) {
        if (mContext == null) return
        val intent = Intent(mContext, T::class.java)
        IntentKtx.addPair(intent, *pair)
        startActivityForResult(intent, callback)
    }

    fun startActivityForResult(
        clazz: Class<*>,
        vararg pair: Pair<String, Any?>,
        callback: ((Int, Intent?) -> Unit)?
    ) {
        mContext?.run {
            val intent = Intent(this, clazz)
            IntentKtx.addPair(intent, *pair)
            startActivityForResult(intent, callback)
        }
    }

    fun startActivityForResult(
        clazz: Class<*>,
        callback: ((Int, Intent?) -> Unit)?,
        vararg pair: Pair<String, Any?>
    ) {
        mContext?.run {
            val intent = Intent(this, clazz)
            IntentKtx.addPair(intent, *pair)
            startActivityForResult(intent, callback)
        }
    }

    /**
     * 对Intent跳转,带参数
     * @param intent  参数提取放入intent中
     * @param callback
     */
    fun startActivityForResult(intent: Intent, callback: ((Int, Intent?) -> Unit)?) {
        mRouterFragment?.run {
            startActivityForResult(intent, callback)
        }
    }


}