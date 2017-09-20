package com.huim_lin.trust.net

import android.app.Activity
import com.huim_lin.trust.bean.User

/**
 * request data
 * Created by huim_lin on 2017/9/19.
 */
object RequestUtils {

    val baseUrl="http://10.20.195.25:8080/"

    interface OnLoginListener{
        fun onError(msg:String)
        fun onSuccess(user:User)
    }

    fun onLogin(activity: Activity,name:String,pass:String,listener:OnLoginListener){
    }

    fun onRegister(activity: Activity,name:String,pass:String,listener:OnLoginListener){
    }

    interface OnGetUserListListener{
        fun onError(msg: String)
        fun onSuccess(list: List<User>)
    }

    fun onGetUserList(activity: Activity,listener: OnGetUserListListener){
    }
}