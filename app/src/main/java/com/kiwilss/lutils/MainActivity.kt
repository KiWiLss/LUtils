package com.kiwilss.lutils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.kiwilss.lutils.help.jump.makeCall
import com.kiwilss.lutils.ktx.mk
import com.kiwilss.lutils.ktx.string
import com.kiwilss.lutils.ui.BroadcastActivity
import com.kiwilss.lutils.ui.TextViewActivity
import com.kiwilss.lutils.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMainTv.setOnClickListener {
            startActivity(Intent(this,TextViewActivity::class.java))
        }

        btnMainCall.setOnClickListener {
            //makeCall("18657194108")
//            startActivity(Intent(this,BroadcastActivity::class.java))
            Log.e("MMM", ": network; isUse = ${NetworkUtils.isNetworkAvailable(this)}" +
                    ",isConnect = ${NetworkUtils.isNetworkConnected(this)}" +
                    ", isWIfi= ${NetworkUtils.isWifi(this)} -- ${NetworkUtils.isWifiConnected(this)}" +
                    ", isMobile = ${NetworkUtils.isMobile(this)} -- ${NetworkUtils.isMobileConnected(this)}" +
                    ", type = ${NetworkUtils.getConnectedType(this)}" +
                    ", type = ${NetworkUtils.getNetworkInfoType(this)}" );
//            startActivityForResult(Intent(Settings.ACTION_WIFI_SETTINGS),9); //直接进入手机中的wifi网络设置界面

        }

        /*mk().encode("key","key")
        val key = mk().string("key")
        Log.e("MMM", ": $key" );*/




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("MMM", ": onActivityResult: $resultCode" );
    }
}