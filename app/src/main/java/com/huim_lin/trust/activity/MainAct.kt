package com.huim_lin.trust.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.huim_lin.trust.R
import org.jetbrains.anko.startActivity

class MainAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
//        IntentUtils.go2Activity(this,LoginAct::class.java)
        startActivity<LoginAct>()
        finish()
    }
}
