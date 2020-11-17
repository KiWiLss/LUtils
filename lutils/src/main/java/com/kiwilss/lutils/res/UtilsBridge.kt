package com.kiwilss.lutils.res

import com.kiwilss.lutils.LUtilsConfig.getContext

 object UtilsBridge {
    val statusBarHeight: Int
        get() {
            val resources =
                getContext().resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }

    val navBarHeight: Int
        get() {
            val res =
                getContext().resources
            val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId != 0) {
                res.getDimensionPixelSize(resourceId)
            } else {
                0
            }
        }
}