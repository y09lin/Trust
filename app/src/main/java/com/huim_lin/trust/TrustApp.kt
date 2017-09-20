package com.huim_lin.trust

import android.app.Application
import cn.jpush.android.api.JPushInterface

/**
 * trust app
 * Created by huim_lin on 2017/9/19.
 */
class TrustApp : Application() {

    override fun onCreate() {
        super.onCreate()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}