package com.huim_lin.trust.activity

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import cn.jpush.android.api.JPushInterface
import cn.jpush.android.api.TagAliasCallback
import com.huim_lin.trust.R
import com.huim_lin.trust.bean.User
import com.huim_lin.trust.net.RequestUtils
import kotlinx.android.synthetic.main.act_login.*
import org.jetbrains.anko.*

class LoginAct : AppCompatActivity(),AnkoLogger {
    private var isLogin=true
    private var user:User?=null
    private var dialog:ProgressDialog?=null

    private val log= AnkoLogger("Test")

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

    private fun onLogin(name:String,pass:String){
        if (dialog==null){
            dialog=indeterminateProgressDialog(message = getString(R.string.login_progress))
        }else{
            dialog!!.show()
        }
        RequestUtils.onLogin(this,name,pass,object: RequestUtils.OnLoginListener {
            override fun onError(msg: String) {
                toast(msg)
                dialog!!.dismiss()
                log.debug("RequestUtils")
                debug("RequestUtils")
            }

            override fun onSuccess(user: User) {
                dialog!!.dismiss()
                Log.d("LoginResult",user.id+" "+user.name)
                this@LoginAct.user=user
                JPushInterface.setAlias(this@LoginAct,user.name,object: TagAliasCallback {
                    override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
                        Log.d("LoginResult","setAlias:"+p1)
                    }
                })
                loginSuccess()
            }
        })
    }

    private fun onRegister(name:String,pass: String){
        RequestUtils.onRegister(this,name,pass,object: RequestUtils.OnLoginListener {
            override fun onError(msg: String) {
                toast(msg)
            }

            override fun onSuccess(user: User) {
                Log.d("LoginResult",user.id+" "+user.name)
                this@LoginAct.user=user
                JPushInterface.setAlias(this@LoginAct,user.name,object: TagAliasCallback {
                    override fun gotResult(p0: Int, p1: String?, p2: MutableSet<String>?) {
                        Log.d("LoginResult","setAlias:"+p1)
                    }
                })
                loginSuccess()
            }
        })
    }

    private fun loginSuccess(){
        alert("Login Success", "Login") {
            yesButton {
                startActivity<HomeAct>(Pair(HomeAct.ERX_USER,user!!))
                finish()
            }
            noButton {}
        }.show()

    }
}
