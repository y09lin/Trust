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

    private val baseUrl="http://10.20.195.51:8080"

    interface OnLoginListener{
        fun onError(msg:String)
        fun onSuccess(user:User)
    }

    fun onLogin(activity: Activity,name:String,pass:String,listener:OnLoginListener){
        val url= "/user/login"
        val requestBody=FormBody.Builder()
                .add("name",name)
                .add("password",pass)
                .build()
        commonInvoke(url, requestBody, object: RequestListener {
            override fun onError(e: IOException) {
                activity.runOnUiThread {
                    listener.onError(activity.getString(R.string.net_error))
                }
            }

            override fun onResponse(response: String) {
                activity.runOnUiThread {
                    val gson= Gson()
                    val result=gson.fromJson(response,LoginResult::class.java)
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
        val url= "/user/register"
        val requestBody=FormBody.Builder()
                .add("name",name)
                .add("password",pass)
                .build()
        commonInvoke(url, requestBody, object: RequestListener {
            override fun onError(e: IOException) {
                activity.runOnUiThread {
                    listener.onError(activity.getString(R.string.net_error))
                }
            }

            override fun onResponse(response: String) {
                activity.runOnUiThread {
                    val gson= Gson()
                    val result=gson.fromJson(response,LoginResult::class.java)
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
        val url= "/user/getAllUser"
        val requestBody=FormBody.Builder()
                .add("name",name)
                .build()
        commonInvoke(url, requestBody, object: RequestListener {
            override fun onError(e: IOException) {
                activity.runOnUiThread {
                    listener.onError(activity.getString(R.string.net_error))
                }
            }

            override fun onResponse(response: String) {
                activity.runOnUiThread {
                    val gson= Gson()
                    val result=gson.fromJson(response,LoginResult::class.java)
                    if (result.code == "s_0"){
                        listener.onSuccess(result.result!!)
                    }else{
                        listener.onError(result.message!!)
                    }
                }
            }
        })
    }

    private fun commonInvoke(url: String, requestBody: RequestBody, listener: RequestListener){
        val requestUrl= baseUrl+url
        val httpClien=OkHttpClient()
        val request=Request.Builder().url(requestUrl).post(requestBody).build()
        Log.d("Request","url="+requestUrl)
        httpClien.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                listener.onError(e!!)
            }

            override fun onResponse(call: Call?, response: Response) {
                listener.onResponse(response.body()!!.string())
            }
        })

    }

    interface RequestListener{
        fun onError(e: IOException)
        fun onResponse(response: String)
    }
}