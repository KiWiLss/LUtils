package com.kiwilss.lutils

import android.app.Application

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/6/13
 * @desc   : {DESCRIPTION}
 */
class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        LUtilsConfig.init(this)
    }
}