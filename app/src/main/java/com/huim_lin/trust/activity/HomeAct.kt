package com.huim_lin.trust.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.huim_lin.trust.R
import com.huim_lin.trust.adapter.UserAdapter
import com.huim_lin.trust.bean.User
import com.huim_lin.trust.net.RequestUtils
import com.huim_lin.trust.utils.ToastUtils

class HomeAct : AppCompatActivity() {
    private var beanList:MutableList<User>?=null
    private var adapter:UserAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)

        initData()
    }

    private fun initData() {
        beanList=ArrayList<User>()
        adapter= UserAdapter(beanList!!,this)
        RequestUtils.onGetUserList(this,object: RequestUtils.OnGetUserListListener {
            override fun onError(msg: String) {
                ToastUtils.show(this@HomeAct,msg)
            }

            override fun onSuccess(list: List<User>) {
                beanList!!.clear()
                beanList!!.addAll(list)
                adapter!!.notifyDataSetChanged()
            }
        })
    }
}
