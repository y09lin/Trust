package com.huim_lin.trust.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.huim_lin.trust.R
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : AppCompatActivity() {
    private var isLogin=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)
        bindEvents()
        // TODO edit -- keyboard
    }

    private fun bindEvents(){
        btn_login.setOnClickListener {
            val name=edit_name.text.toString().trim()
            val pass=edit_pass.text.toString().trim()
            if (TextUtils.isEmpty(name)){
                edit_name.error=getString(R.string.hint_login_account)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(pass)){
                edit_pass.error = getString(R.string.hint_login_password)
                return@setOnClickListener
            }
            if (isLogin){
                onLogin(name,pass)
            }else{
                val pass2=edit_pass2.text.toString().trim()
                if (TextUtils.isEmpty(pass2) || pass2 != pass){
                    edit_pass2.error=getString(R.string.hint_login_password2)
                    return@setOnClickListener
                }
                onRegister(name,pass)
            }
        }
        text_login.setOnClickListener {
            isLogin=!isLogin
            if (isLogin){
                btn_login.text=getString(R.string.login)
                text_login.text=getString(R.string.login_no_account)
                edit_pass2.visibility=View.GONE
            }else{
                btn_login.text=getString(R.string.register)
                text_login.text=getString(R.string.register_have_account)
                edit_pass2.visibility=View.VISIBLE
            }
        }
    }

    private fun onLogin(name:String,pass:String){}

    private fun onRegister(name:String,pass: String){}

    private fun loginSuccess(){}
}
