package com.kiwilss.lutils.math

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.floor

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/5/18
 * @desc   : {随机数相关}
 */
object RandomUtils {

    fun getRandom(): Random {
        return Random()
    }

    /**
     * 随机数Int的生成,随机数生成无边界的Int
     */
    fun getRandomForIntegerUnbounded(): Int {
        return getRandom().nextInt()
    }

    /**
     * 生成有边界的Int
     * @param min
     * @param max
     * @return
     */
    fun getRandomForIntegerBounded(min: Int, max: Int): Int {
        return min + (Random().nextFloat() * (max - min)).toInt()
    }

    /**
     * 使用TreadLocalRandom来生成有边界的Int,包含min而不包含max
     * @param min
     * @param max
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun getRandomForIntegerBounded2(min: Int, max: Int): Int {
        return ThreadLocalRandom.current().nextInt(min, max)
    }

    /**
     * 随机数生成无边界的Long
     */
    fun getRandomForLongUnbounded(): Long {
        return Random().nextLong()
    }

    /**
     * 使用Random生成有边界的Long
     * 因为Random类使用的种子是48bits，所以nextLong不能返回所有可能的long值，long是64bits。
     * @param min
     * @param max
     * @return
     */
    fun getRandomForLongBounded(min: Long, max: Long): Long {
        return min + (Random().nextDouble() * (max - min)).toLong()
    }

    /**
     *使用ThreadLocalRandom生成有边界的Long
     * @param min
     * @param max
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getRandomForLongBounded2(min: Long, max: Long): Long {
        return ThreadLocalRandom.current().nextLong(min, max)
    }

    /**
     * 随机数Float的生成, 随机数Float生成[0.0-1.0)之间的Float随机数
     */
    fun getRandomForFloat0To1(): Float {
        return Random().nextFloat()
    }

    /**
     * 随机数Float的生成, 随机数Float的生成生成[min,max)之间的Float随机数
     */
    fun getRandomForFloatBounded(min: Float, max: Float): Float {
        return min + Random().nextFloat() * (max - min)
    }
    // 使用ThreadLocalRandom生成有边界的Float随机数
    // ThreadLocalRandom类没有提供
    /**
     * 随机数Double的生成,生成0.0d-1.0d之间的Double随机数
     */
    fun getRandomForDouble0To1(): Double {
        return Random().nextDouble()
    }

    /**
     * 随机数Double的生成,生成[min,max)之间的Double随机数
     */
    fun getRandomForDoubleBounded(min: Double, max: Double): Double {
        return min + Random().nextDouble() * (max - min)
    }

    /**
     *使用ThreadLocalRandom生成有边界的Double随机数
     * @param min
     * @param max
     * @return
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getRandomForDoubleBounded2(min: Double, max: Double): Double {
        return ThreadLocalRandom.current().nextDouble(min, max)
    }

}


