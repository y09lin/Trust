package com.huim_lin.trust.utils

import android.content.Context
import android.content.Intent

/**
 * 调转工具
 * Created by huim_lin on 2017/9/19.
 */
object IntentUtils {
    fun go2Activity(context: Context,clazz: Class<*>){
        context.startActivity(Intent(context,clazz))
    }
}