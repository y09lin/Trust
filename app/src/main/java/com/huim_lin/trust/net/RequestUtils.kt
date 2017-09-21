package com.huim_lin.trust.net

import android.app.Activity
import android.util.Log
import com.google.gson.Gson
import com.huim_lin.trust.R
import com.huim_lin.trust.bean.User
import okhttp3.*
import java.io.IOException

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
        val url= baseUrl+"/user/login"
        val httpClien=OkHttpClient()
        val requestBody=FormBody.Builder()
                .add("name",name)
                .add("password",pass)
                .build()
        val request=Request.Builder().url(url).post(requestBody).build()
        httpClien.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread{
                    listener.onError(activity.getString(R.string.net_error))
                }
            }

            override fun onResponse(call: Call?, response: Response) {
                val str=response.body()!!.string()
                val gson= Gson()
                activity.runOnUiThread {
                    Log.d("LoginResult",str)
                    val result=gson.fromJson(str,LoginResult::class.java)
                    if (result.code == "s_0"){
                        listener.onSuccess(result.result!![0])
                    }else{
                        listener.onError(result.message!!)
                    }
                }
            }
        })
    }

    fun onRegister(activity: Activity,name:String,pass:String,listener:OnLoginListener){
        val url= baseUrl+"/user/register"
        val httpClien=OkHttpClient()
        val requestBody=FormBody.Builder()
                .add("name",name)
                .add("password",pass)
                .build()
        val request=Request.Builder().url(url).post(requestBody).build()
        httpClien.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread{
                    listener.onError(activity.getString(R.string.net_error))
                }
            }

            override fun onResponse(call: Call?, response: Response) {
                val str=response.body()!!.string()
                val gson= Gson()
                activity.runOnUiThread {
                    Log.d("LoginResult",str)
                    val result=gson.fromJson(str,LoginResult::class.java)
                    if (result.code == "s_0"){
                        listener.onSuccess(result.result!![0])
                    }else{
                        listener.onError(result.message!!)
                    }
                }
            }
        })
    }

    interface OnGetUserListListener{
        fun onError(msg: String)
        fun onSuccess(list: List<User>)
    }

    fun onGetUserList(activity: Activity, name:String, listener: OnGetUserListListener){
        val url= baseUrl+"/user/getAllUser"
        val httpClien=OkHttpClient()
        val requestBody=FormBody.Builder()
                .add("name",name)
                .build()
        val request=Request.Builder().url(url).post(requestBody).build()
        httpClien.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread{
                    listener.onError(activity.getString(R.string.net_error))
                }
            }

            override fun onResponse(call: Call?, response: Response) {
                val str=response.body()!!.string()
                val gson= Gson()
                activity.runOnUiThread {
                    Log.d("GetUser",str)
                    val result=gson.fromJson(str,LoginResult::class.java)
                    if (result.code == "s_0"){
                        listener.onSuccess(result.result!!)
                    }else{
                        listener.onError(result.message!!)
                    }
                }
            }
        })
    }
}