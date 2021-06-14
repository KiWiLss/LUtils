package com.kiwilss.lutils.ui

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kiwilss.lutils.R
import com.kiwilss.lutils.tv.*
import kotlinx.android.synthetic.main.activity_textview.*

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/6/13
 * @desc   : {DESCRIPTION}
 */
class TextViewActivity: AppCompatActivity(R.layout.activity_textview) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //第一种,对要设置的内容 src 是一段字符串,已经是一段完整的内容
        //对整个 text 设置方式一
        tvTvOne.sizeSpan(textSize = 20f)
        //对整个 text 设置方式二
        tvTvOne2.typeSpan(src = "全部文字加粗",target = "全部文字加粗",
            type = SsbKtx.type_bold)

        //设置部分文字效果
        //tvTv2.typeSpan(range = 2..4,type = SsbKtx.type_bold)
        tvTv2.typeSpan(target = "部分",type = SsbKtx.type_bold)
        //设置加粗倾斜效果
        tvTv3.typeSpan(range = 0..4,type = SsbKtx.type_bold_italic)

        //加粗倾斜和文字颜色
        tvTv4.typeSpan(range = 0..4,type = SsbKtx.type_bold_italic)
            .foregroundColorIntSpan(range = 0..4,color = Color.GREEN)
            .strikethroughSpan(range = 0..4)
        //对多个部分分别设置
        tvTv5.typeSpan(range = 0..4,type = SsbKtx.type_bold_italic)
            .foregroundColorIntSpan(range = 7..11,color = Color.BLUE)

        //设置点击
        tvTv6.clickIntSpan(range = 0..4){
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        }
        //设置超链接
        tvTv7.urlSpan(range = 0..4,url = "https://www.baidu.com")
//        val ssb = SsbUtils.Builder("设置超链接","超链接")
//            .url("https://www.baidu.com").build()
//        tvTv7.movementMethod = LinkMovementMethod.getInstance()
//        tvTv7.text = ssb

        //第二种,对要设置的内容 src 拼接使用


    }
}