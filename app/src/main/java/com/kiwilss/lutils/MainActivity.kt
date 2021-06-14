package com.kiwilss.lutils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiwilss.lutils.ui.TextViewActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMainTv.setOnClickListener {
            startActivity(Intent(this,TextViewActivity::class.java))
        }


    }
}