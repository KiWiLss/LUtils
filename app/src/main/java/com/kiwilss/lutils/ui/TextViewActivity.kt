package com.kiwilss.lutils.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kiwilss.lutils.R
import com.kiwilss.lutils.help.jump.ActivityHelper
import com.kiwilss.lutils.help.jump.createActivityIntent
import com.kiwilss.lutils.help.jump.startActivityForResultK
import com.kiwilss.lutils.ktx.isNotNullOrEmpty
import com.kiwilss.lutils.ktx.putString
import com.kiwilss.lutils.ktx.sp
import com.kiwilss.lutils.tv.*
import kotlinx.android.synthetic.main.activity_textview.*

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/6/13
 * @desc   : {DESCRIPTION}
 */
class TextViewActivity: AppCompatActivity(R.layout.activity_textview) {
    companion object{

        fun starter(context: Context, tv: String?, hai: String?, callback: ((Int, Intent?) -> Unit)?){
            val intent = context.createActivityIntent<TextViewActivity>("tv" to tv, "hai" to hai)
            ActivityHelper.init(context)
                ?.startActivityForResult(intent,callback)
        }
        fun starter2(context: Context, tv: String?, hai: String?, callback: ((Int, Intent?) -> Unit)?){
            val intent = context.createActivityIntent<TextViewActivity>("tv" to tv, "hai" to hai)
            context.startActivityForResultK(intent,callback)
        }
    }

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

        //加粗倾斜和文字颜色,对同一个部分做多种效果,只能第一个设置 src
//        tvTv4.typeSpan(range = 0..4,type = SsbKtx.type_bold_italic)
//            .foregroundColorIntSpan(range = 0..4,color = Color.GREEN)
//            .strikethroughSpan(range = 0..4)
        tvTv4.typeSpan(src = "只能这个可以设置 src,后面的再设置会导致前面效果无效",
            range = 0..4,type = SsbKtx.type_bold_italic)
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
        tvTv8.text = "拼接一段文字"
        tvTv8.appendTypeSpan("加粗",SsbKtx.type_bold)
            .strikethroughSpan(target = "加粗")//对同一部分文字做多个效果
            .appendForegroundColorIntSpan("改变字体颜色",Color.RED)


        "f".isNotNullOrEmpty()
    }

    private fun testSp() {
        //保存常规数据
        //保存一个
        sp().putString("key","hhhhh")
        //保存多个
        /*    sp().edit {
                putInt(SpKey.TEST,2)
                putString(SpKey.HELLO,"hello")
            }*/
        //获取保存常规数据
        /*  val hello = sp().getString(SpKey.TEST,"")
          Log.e("MMM", "testSp: $hello" )*/
        //保存对象
/*      sp().putAny(SpKey.TEST,TestBean(true,"alice"))
      //获取保存对象
      val test = sp().getAny<TestBean>(SpKey.TEST)
      Log.e("MMM", "testSp: ${test?.name}" )*/
        //保存list
        /*  val list = listOf(TestBean(true,"alice"),TestBean(true,"bob"))
          sp().putAny(SpKey.TEST,list)*/
        //获取保存的list
        /*   val testList = sp().getList<TestBean>(SpKey.TEST)
           Log.e("MMM", "testSp: $testList" )*/

        /* //清除某个数据
         sp().clear(SpKey.HELLO)
         //清除当前name下的所有数据
         sp().clear()*/

        //sp().getAny("",TestBean::class.java)
    }
}