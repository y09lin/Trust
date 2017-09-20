package com.huim_lin.trust.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cn.jpush.android.api.JPushInterface


class JPushReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        when(intent.action){
            "cn.jpush.android.intent.REGISTRATION" ->{
                Log.d("JPushReceiver", bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID))
            }
            "cn.jpush.android.intent.MESSAGE_RECEIVED" -> {
                Log.d("JPushReceiver", "title:"+bundle.getString(JPushInterface.EXTRA_TITLE))
                Log.d("JPushReceiver", "message:"+bundle.getString(JPushInterface.EXTRA_MESSAGE))
                Log.d("JPushReceiver", "extra:"+bundle.getString(JPushInterface.EXTRA_EXTRA))
                Log.d("JPushReceiver", "msgId:"+bundle.getString(JPushInterface.EXTRA_MSG_ID))
            }
            "cn.jpush.android.intent.NOTIFICATION_RECEIVED" -> {
                Log.d("JPushReceiver", "notification title:"+bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE))
                Log.d("JPushReceiver", "notification content:"+bundle.getString(JPushInterface.EXTRA_ALERT))
                Log.d("JPushReceiver", "notification id:"+bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID))
            }
            "cn.jpush.android.intent.CONNECTION" -> {
                Log.d("JPushReceiver", "CONNECTION:"+ bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE, false))
            }
        }
    }
}
