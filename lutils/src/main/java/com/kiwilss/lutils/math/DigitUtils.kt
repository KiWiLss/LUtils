package com.kiwilss.lutils.math

import java.text.DecimalFormat

object DigitUtils{

    const val five = "#.00000"
    const val four = "#.0000"
    const val two = "#.00"

    /**
     * stirng -> double
     * 整数默认会有一位小数,90 -->  90.0
     * 有小数有多少位显示多少位, 90.023 --> 90.023
     * 90.00会保留一位 --->  90.0
     */
    fun string2Double(string: String?): Double?{
        if (string.isNullOrEmpty()) {
            return null
        }
        return string.toDouble()
    }

    fun getDoubleFormat(pattern: String): DecimalFormat{
        return DecimalFormat(pattern)
    }

    /**
     * 默认保留2位,会四舍五入
     * string: 传入的数字
     * pattern: 转成double类型格式
     */
    fun string2StringRound(string: String?, pattern: String = two): String{
        if (string.isNullOrEmpty()) {
            return ""
        }
        val dd = string2Double(string)
        val doubleFormat = getDoubleFormat(pattern)
        return doubleFormat.format(dd)
    }
    /**
     * 默认保留2位,会四舍五入
     * dd: 传入的数字
     * pattern: 转成double类型格式
     */
    fun double2StringRound(dd: Double?, pattern: String = two): String{
        if (dd == null){
            return ""
        }
        val doubleFormat = getDoubleFormat(pattern)
        return doubleFormat.format(dd)
    }


    /**
     * double --> string 保留dig位,不四舍五入
     * d: 数据源
     * dig: 保留小数点后几位
     */
    fun double2String(d: Double?, dig: Int = 2): String{
        if (d == null) {
            return ""
        }
        val doubleFormat = getDoubleFormat(five)
        val result = doubleFormat.format(d)

        var index = result.indexOf(".")
        if (index > result.length){
            index = result.length
        }
        var end = index + dig + 1
        if (end > result.length){
            end = result.length
        }
        return result.subSequence(0, end).toString()
    }
    /**
     * string --> string 保留dig位,不四舍五入
     * d: 数据源
     * dig: 保留小数点后几位
     */
    fun string2String(string: String?, dig: Int = 2): String{
        if (string.isNullOrEmpty()) {
            return ""
        }
        val dd = string2Double(string)
        return double2String(dd,dig)
    }

    /**
     * doulble  ---->  string
     * .0 会去除掉
     */
    fun doubleWipeZero(double: Double?): String{
        if (double == null){
            return ""
        }
        val decimalFormat =
                DecimalFormat("###################.###########")
        return decimalFormat.format(double)
    }


}