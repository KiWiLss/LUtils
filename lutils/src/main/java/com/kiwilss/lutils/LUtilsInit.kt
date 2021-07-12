package com.kiwilss.lutils

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/7/12
 * @desc   : {DESCRIPTION}
 */
class LUtilsInit: Initializer<Unit> {
    override fun create(context: Context) {
        LUtilsConfig.init(context)
        MMKV.initialize(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}