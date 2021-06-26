package com.kiwilss.lutils

import android.annotation.SuppressLint
import android.content.Context
import com.kiwilss.lutils.res.DensityUtils
import java.io.Serializable

/**
 * @author : Lss kiwilss
 * @FileName: LUtilsConfig
 * @e-mail : kiwilss@163.com
 * @time : 2020/7/27
 * @desc : {DESCRIPTION}
 */
@SuppressLint("StaticFieldLeak")
object LUtilsConfig {
    const val SP_DEFAULT_NAME = "sp_default_name"
    lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
        DensityUtils.screenDpi
    }

    fun getContext() = mContext


}