package com.kiwilss.lutils.utils

import android.net.Uri

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/7/2
 * @desc   : {DESCRIPTION}
 */
object UriUtils {
    /**
     *通过 uri 解析网址各部分内容
     * @param url
     * @param action
     * @return
     */
    fun parseUrlByUri(url: String?, action: String?): String? {
        if (url.isNullOrEmpty() || action.isNullOrEmpty()) return null
        val uri = Uri.parse(url)
        return uri.getQueryParameter(action)
    }
}