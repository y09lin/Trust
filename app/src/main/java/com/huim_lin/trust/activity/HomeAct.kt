package com.huim_lin.trust.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.huim_lin.trust.R
import com.huim_lin.trust.adapter.UserAdapter
import com.huim_lin.trust.bean.User
import com.huim_lin.trust.net.RequestUtils
import com.huim_lin.trust.utils.ToastUtils
import kotlinx.android.synthetic.main.act_home.*

class HomeAct : AppCompatActivity() {
    companion object {
        val ERX_USER="erx_user"
    }
    private var beanList:MutableList<User>?=null
    private var adapter:UserAdapter?=null
    private var user:User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)

        initData()
        getData()
    }

    private fun initData() {
        user= intent.getSerializableExtra(ERX_USER) as User?
        beanList=ArrayList()
        adapter= UserAdapter(beanList!!,this)
        list_user.adapter=adapter
        text_name.text = user!!.name
        text_score.text=user!!.score
        text_challenge.text= Html.fromHtml(getString(R.string.challenge_count,user!!.challenge))
        text_against.text=Html.fromHtml(getString(R.string.against_count,user!!.against))
    }

    private fun getData(){
        RequestUtils.onGetUserList(this, user!!.name!!,object: RequestUtils.OnGetUserListListener {
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
