package com.kiwilss.lutils.ktx

import com.kiwilss.lutils.utils.UriUtils
import java.util.regex.Pattern

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/7/2
 * @desc   : {CharSequence 扩展函数}
 */
object CharSequenceKtx {
    //匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效
    const val SPACE = "[\\s]"

    //匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效
    const val UN_SPACE = "[\\S]"

    //数字字符匹配。等效于 [0-9]
    const val DIGITS = "[\\d]"

    //非数字字符匹配。等效于 [^0-9]
    const val UN_DIGITS = "[\\D]"

    //匹配任何字类字符，包括下划线。与"[A-Za-z0-9_]"等效
    const val DIGITS_LETTER_UNDERLINE = "[\\w]"

    //与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效
    const val UN_DIGITS_LETTER = "[\\W]"

    //匹配大写字母
    const val UPPERCASE_LETTER = "[A-Z]"

    //匹配小写字母
    const val LOWER_LETTER = "[a-z]"

    //匹配字母
    const val LETTER = "[a-zA-Z]"

    //匹配字母和数字
    const val DIGITS_LETTER = "[a-zA-Z0-9]"

    //匹配汉字
    const val CHINESE = "[\u4e00-\u9fa5]"

    //匹配特殊字符
    const val SPECIAL_CHARACTERS =
        "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t"
}

fun testSpace(): Unit {
    val test: String = "hello"
    val test2 = "noSpace"
    val test3 = " 2321fdf汉字 --9**&"
    val test4 = " he llo "
    val test5 = "函数 zifu 2134"
    println("----------------")

    println(test4.removePrefix(" "))//移除前缀
    println(test4.removeSuffix(" "))//移除后缀
/*    println(test3.stringSize("2"))
    println(test.stringSize("2"))
    println(test.stringSize("l"))*/

/*    println(test5.replace(Regex("[\\d]"), "*"))//数字替换

    println(test5.replace(Regex(CharSequenceKtx.SPACE), ""))//删除所有空格
    println(test4.trim())
    println(test3.substringBefore("f"))//截取指定字符串之前的内容*/


/*    println(test5.toUpperCase())//字母大写
    println(test.capitalize())//首字母大写
    println(test2.decapitalize())//首字母小写
    println(test2.toLowerCase())//字母小写*/

//    println(test5.contains(Regex(CharSequenceKtx.DIGITS)))
//    println(test5.contains(Regex(CharSequenceKtx.UPPERCASE_LETTER)))
/*    println(test.matches(Regex("[a-z]*")))//是否全是小写字母
    println(test2.matches(Regex("[a-z]*")))//是否全是小写字母

    println(test.contains(Regex(CharSequenceKtx.LOWER_LETTER)))
    println(test.matches(Regex("${CharSequenceKtx.LOWER_LETTER}*")))
    println("${CharSequenceKtx.LOWER_LETTER}*")
    println(test5.contains(Regex(CharSequenceKtx.CHINESE)))
    println(test5.matches(Regex(CharSequenceKtx.CHINESE + "*")))*/
//    println(test2.first().toUpperCase() + test2.substring(1))
    //过滤非数字
    /*  println(test3.filter {
          it.isDigit()
      })*/
    /*  println(test3.substringUnDigits())
      println(test2.substringUnDigits())*/
//    println(test.substringDigits())
//    println(test5.substringDigits())

}

/**
 *CharSequence不为 null 或者 empty
 */
fun CharSequence?.isNotNullOrEmpty() = !isNullOrEmpty()

/**
 *〈指定裁剪出str中open和close包含着的那部分字符串〉
 * @param open
 * @param close
 * @return
 */
fun CharSequence?.substringBetween(open: String?, close: String?): String? {
    if (isNullOrEmpty() || open.isNullOrEmpty() || close.isNullOrEmpty()) return null
    val start = this!!.indexOf(open)
    if (start != -1) {
        val end = indexOf(close, start + open.length)
        if (end != -1) {
            return substring(start + open.length, end)
        }
    }
    return null
}

fun CharSequence?.substringBetween(tag: String?) = substringBetween(tag, tag)

/**
 *首字母大写，或者调用capitalize
 * @return
 */
fun CharSequence.toFirstUpperCase(): CharSequence {
    if (isEmpty()) {
        return ""
    }
    return first().toUpperCase() + substring(1)
}

/**
 *首字母小写，或者调用decapitalize
 * @return
 */
fun CharSequence.toFirstLowerCase(): CharSequence {
    if (isEmpty()) {
        return ""
    }
    return first().toLowerCase() + substring(1)
}

/**
 *截取非数字，只能截取第一段连续的，不在首位截取不到
 * @return
 */
fun CharSequence.substringUnDigits(): CharSequence {
    val pattern = Pattern.compile("\\D+")
    val matcher = pattern.matcher(this)
    while (matcher.find()) {
        return matcher.group(0)
    }
    return ""
}

/**
 *截取数字，只能截取第一段连续的数字
 * @return
 */
fun CharSequence.substringDigits(): CharSequence {
    val pattern = Pattern.compile("\\d+")
    val matcher = pattern.matcher(this)
    while (matcher.find()) {
        return matcher.group(0)
    }
    return ""
}

/**
 *截取数字，截取所有的数字
 * @return
 */
fun CharSequence.substringAllDigits(): CharSequence {
    val pattern = Pattern.compile("\\d+")
    val matcher = pattern.matcher(this)
    val sb = StringBuilder()
    while (matcher.find()) {
        sb.append(matcher.group())
    }
    return sb
}

/**
 *特定字符串的数量
 * @param tag
 * @return
 */
fun CharSequence.stringSize(tag: String): Int {
    return split(tag).size - 1
}

/**
 *含有特定字符的数量
 * @param char
 * @return
 */
fun CharSequence.charSize(char: Char): Int {
    var count = 0
    forEach {
        if (it == char) {
            count++
        }
    }
    return count
}

/**
 *含有空格数量
 * @return
 */
fun CharSequence.spaceSize(): Int {
    var count = 0
    forEach {
        if (it.isSpace()) {
            count++
        }
    }
    return count
}

/**
 *是否含有特殊字符,true有，false没有
 */
fun CharSequence.isHasSpecial() = contains(Regex(CharSequenceKtx.SPECIAL_CHARACTERS))

/**
 *是否含有汉字,true有，false没有
 */
fun CharSequence.isHasChinese() = contains(Regex(CharSequenceKtx.CHINESE))

/**
 *是否全是汉字
 */
fun CharSequence.isChinese() = matches(Regex(CharSequenceKtx.CHINESE + "+"))

/**
 *是否含有小写字母,true有，false没有
 */
fun CharSequence.isHasLower() = contains(Regex(CharSequenceKtx.LOWER_LETTER))

/**
 *是否全是大写字母
 */
fun CharSequence.isLower() = matches(Regex(CharSequenceKtx.LOWER_LETTER + "+"))

/**
 *是否含有大写字母,true有，false没有
 */
fun CharSequence.isHasCapital() = contains(Regex(CharSequenceKtx.UPPERCASE_LETTER))

/**
 *是否全是大写字母
 */
fun CharSequence.isCapital() = matches(Regex(CharSequenceKtx.UPPERCASE_LETTER + "+"))

/**
 *是否含有字母，不区分大小写，true有，false没有
 */
fun CharSequence.isHasLetter() = contains(Regex(CharSequenceKtx.LETTER))

/**
 *是否全是字母
 */
fun CharSequence.isLetter() = matches(Regex(CharSequenceKtx.LETTER + "+"))

/**
 *是否含有数字，true有，false没有
 */
fun CharSequence.isHasDigit() = contains(Regex(CharSequenceKtx.DIGITS))

/**
 *是否全是数字，也可以用isDigitsOnly
 */
fun CharSequence.isDigits() = matches(Regex(CharSequenceKtx.DIGITS + "+"))

/**
 *是否全都是数字
 */
fun CharSequence.isDigits2() = Pattern.compile("[\\d]+").matcher(this).matches()

/**
 *是否含有空格，true有，false没有
 */
fun CharSequence.isHasSpace() = contains(Regex(CharSequenceKtx.SPACE))

/**
 *是否全是空格
 */
fun CharSequence.isSpace() = matches(Regex(CharSequenceKtx.SPACE + "+"))

/**
 *判断字符是否是空格
 */
fun Char.isSpace() = this.toString().isHasSpace()

/**
 *通过 uri 解析网址各部分内容
 * @param action
 */
fun CharSequence?.parseUrlByUri(action: String?) = UriUtils.parseUrlByUri(this?.toString(),action)