package com.kiwilss.lutils

import android.content.Context
import com.kiwilss.lutils.res.DensityUtils

/**
 * @author : Lss kiwilss
 * @FileName: LUtilsConfig
 * @e-mail : kiwilss@163.com
 * @time : 2020/7/27
 * @desc : {DESCRIPTION}
 */
object LUtilsConfig{

    lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context

    }

    fun getContext() = mContext


}