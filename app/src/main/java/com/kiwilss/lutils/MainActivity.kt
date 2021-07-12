package com.kiwilss.lutils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kiwilss.lutils.help.jump.makeCall
import com.kiwilss.lutils.ktx.mk
import com.kiwilss.lutils.ktx.string
import com.kiwilss.lutils.ui.BroadcastActivity
import com.kiwilss.lutils.ui.TextViewActivity
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
            startActivity(Intent(this,BroadcastActivity::class.java))
        }

        mk().encode("key","key")
        val key = mk().string("key")
        Log.e("MMM", ": $key" );
    }
}