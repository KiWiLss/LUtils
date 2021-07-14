package com.kiwilss.lutils.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kiwilss.lutils.R
import com.kiwilss.lutils.ktx.registerBroadcast
import com.kiwilss.lutils.ktx.sendBroadcast
import com.kiwilss.lutils.ktx.unregisterBroadcast
import kotlinx.android.synthetic.main.activity_broadcast.*

/**
 *@author : Lss kiwilss
 * @e-mail : kiwilss@163.com
 * @time   : 2021/7/8
 * @desc   : {DESCRIPTION}
 */
class BroadcastActivity: AppCompatActivity(R.layout.activity_broadcast) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       btnSend.setOnClickListener {
            //发送广播信息
//           sendBroadcast(createBroadcastIntent(action1,"broad" to "test broadcast"))
           sendBroadcast(action1,"broad" to "test broadcast")
       }


    }
    //初始化广播
    val mTestBroadcast by lazy { TestBroadcast() }
    val action1 = "com.kiwilss.broadcase1"
    override fun onResume() {
        super.onResume()
        //注册,可以注册很多个 action
        registerBroadcast(mTestBroadcast,action1)
    }

    override fun onPause() {
        super.onPause()
        unregisterBroadcast(mTestBroadcast)
    }
}

class TestBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        Log.e("MMM", "onReceive: $action --- ${intent?.getStringExtra("broad")}");
    }
}