package com.kiwilss.lutils.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket


/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/7/14
 * @desc   : {网络相关工具类}
 *   //需要权限
// <uses-permission android:name="android.permission.INTERNET"></uses-permission>
// <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
 */
object NetworkUtils {
    private var s: Socket? = null

    /**
     * 打开 wifi 设置界面 requestCode
     */
    const val RESULT_WIFI = 9999

    /**
     * 打开设置界面 requestCode
     */
    const val RESULT_SETTING = 99999

    /**
     *通过socket检查外网的连通性,需要在子线程执行
     * @param context
     * @return
     */
    fun isNetworkConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        val connected = null != activeNetworkInfo && activeNetworkInfo.isConnected
        if (!connected) return false
        var routeExists: Boolean
        try {
            if (s == null) {
                s = Socket()
            }
            val host: InetAddress =
                InetAddress.getByName("114.114.114.114") //国内使用114.114.114.114，如果全球通用google：8.8.8.8
            s!!.connect(InetSocketAddress(host, 80), 5000) //google:53
            routeExists = true
            s!!.close()
        } catch (e: IOException) {
            routeExists = false
        }
        return connected && routeExists
    }


    /**判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     * 获取当前的网络状态,子线程执行
     * @return
     * @author suncat
     * @category
     */
    fun ping(): Boolean {
        var result: String? = null
        try {
            val ip = "www.baidu.com" // ping 的地址，可以换成任何一种可靠的外网
            val p = Runtime.getRuntime().exec("ping -c 3 -w 100 $ip") // ping网址3次
            // 读取ping的内容，可以不加
            val input: InputStream = p.inputStream
            val `in` = BufferedReader(InputStreamReader(input))
            val sb = StringBuilder()
            var content: String? = ""
            while (`in`.readLine().also { content = it } != null) {
                sb.append(content)
            }
            // ping的状态
            val status = p.waitFor()
            if (status == 0) {
                result = "success"
                return true
            } else {
                result = "failed"
            }
        } catch (e: IOException) {
            result = "IOException"
        } catch (e: InterruptedException) {
            result = "InterruptedException"
        } finally {
        }
        return false
    }

    /**
     * 获取当前的网络状态,子线程执行
     *   0：当前网络可用
     *  1：需要网页认证的wifi
     *  2：网络不可用的状态
     *  1000：方法错误
     */
    fun ping2(): Int {
        val runtime = Runtime.getRuntime()
        try {
            val p = runtime.exec("ping -c 3 www.baidu.com")
            return p.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 1
    }

    /**
     * 打开网络设置界面
     */
    fun openWiFiSetting(activity: Activity?, requestCode: Int = RESULT_WIFI) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        activity?.startActivityForResult(intent, requestCode)
    }

    /**
     * 打开设置界面
     */
    fun openSetting(activity: Activity?, requestCode: Int = RESULT_SETTING) {
        val intent = Intent(Settings.ACTION_SETTINGS)
        activity?.startActivityForResult(intent, requestCode)
    }

    /**判断是否是移动网络
     * @param context
     * @return
     */
    fun isMobile(context: Context?): Boolean {
        if (context == null) return false
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity.activeNetworkInfo == null) return false
        return connectivity.activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * 判断是否是wifi连接
     */
    fun isWifi(context: Context?): Boolean {
        if (context == null) return false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm.activeNetworkInfo == null) return false
        return cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
    }


    /**
     * 判断MOBILE网络是否连接
     *
     * @param context
     * @return
     */
    fun isMobileConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mMobileNetworkInfo =
                mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 判断WIFI网络是否连接
     *
     * @param context
     * @return
     */
    fun isWifiConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mWiFiNetworkInfo =
                mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     *获得当前的网络信息,返回值 WIFI ,MOBILE
     * @param context
     * @return
     */
    fun getNetworkInfoType(context: Context): String? {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.isConnected) {
                //获得当前的网络信息
                return activeNetworkInfo.typeName
            }
        }
        return null
    }

    /**
     * 获取当前网络连接的类型信息,type = 1 WIFI,type = 0 MOBILE
     *
     * @param context
     * @return
     */
    fun getConnectedType(context: Context?): Int {
        if (context != null) {
            val mConnectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null && mNetworkInfo.isAvailable) {
                return mNetworkInfo.type
            }
        }
        return -1
    }


    /**
     * 判断是否有网络连接,不能判断网络是否可用
     *
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 判断当前网络是否可用,实时
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        var isNetUsable = false
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
            if (networkCapabilities != null) {
                isNetUsable =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            }
        } else {
            isNetUsable = isNetworkConnected(context)
        }
        return isNetUsable
    }

    /**
     * 判断当前网络是否可用，子线程执行
     * @param context
     * @return
     */
    fun isNetworkAvailableAsync(context: Context?): Boolean {
        val isConnected = isNetworkConnected(context)
        return if (isConnected) {
            ping()
        } else {
            false
        }
    }

}