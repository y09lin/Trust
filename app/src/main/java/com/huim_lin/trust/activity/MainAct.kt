package com.huim_lin.trust.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.huim_lin.trust.R
import com.huim_lin.trust.utils.IntentUtils

class MainAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        IntentUtils.go2Activity(this,LoginAct::class.java)
        finish()
    }
}
