package com.huim_lin.trust.net

import com.huim_lin.trust.bean.User

/**
 * request login result
 * Created by huim_lin on 2017/9/21.
 */
class LoginResult :ResponseResult(){
    var result:MutableList<User>?=null
}