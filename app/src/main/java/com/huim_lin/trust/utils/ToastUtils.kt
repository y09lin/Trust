package com.huim_lin.trust.utils

import android.content.Context
import android.widget.Toast

/**
 * show toast
 * Created by huim_lin on 2017/9/19.
 */
object ToastUtils {

    fun show(context: Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

}