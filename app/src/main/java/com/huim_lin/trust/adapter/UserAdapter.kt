package com.huim_lin.trust.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.huim_lin.trust.R
import com.huim_lin.trust.bean.User

/**
 * user list
 * Created by huim_lin on 2017/9/19.
 */
class UserAdapter(private var beanList:List<User>,private val context:Context) :BaseAdapter(){

    override fun getItem(p0: Int): User? {
        if (beanList.isNotEmpty()){
           return beanList[p0]
        }
        return null
    }

    override fun getItemId(p0: Int): Long {
        if (beanList.isEmpty()){
            return -1
        }
        return p0.toLong()
    }

    override fun getCount(): Int {
        return beanList.size
    }

    override fun getView(p: Int, cv: View?, vg: ViewGroup?): View? {
        if (beanList.isEmpty()){
            return null
        }
        var cv=cv
        val vh:ViewHolder
        if (cv==null){
            cv=LayoutInflater.from(context).inflate(R.layout.item_user,null)
            vh=ViewHolder()
            vh.tv_name=cv.findViewById(R.id.text_name)
            vh.tv_score=cv.findViewById(R.id.text_score)
            vh.tv_challenge=cv.findViewById(R.id.text_challenge)
            vh.tv_against=cv.findViewById(R.id.text_against)
            vh.iv_battle=cv.findViewById(R.id.image_battle)
            cv.tag = vh
        }else{
            vh=cv.tag as ViewHolder
        }
        val user=beanList[p]
        vh.tv_name?.text =user.name
        vh.tv_score?.text=user.score
        vh.tv_challenge?.text=Html.fromHtml(context.getString(R.string.challenge_count,user.challenge))
        vh.tv_against?.text=Html.fromHtml(context.getString(R.string.against_count,user.against))

        when(user.battle){
            User.BattleState.N_Battle_normal ->{
                vh.iv_battle?.setImageDrawable(context.resources.getDrawable(R.drawable.open_fight_default))
            }
            User.BattleState.N_Battle_challenge ->{
                vh.iv_battle?.setImageDrawable(context.resources.getDrawable(R.drawable.open_fight))
            }
            User.BattleState.N_Battle_against ->{
                vh.iv_battle?.setImageDrawable(context.resources.getDrawable(R.drawable.battle))
            }
        }

        return cv
    }

    inner class ViewHolder{
        var tv_name:TextView?=null
        var tv_score:TextView?=null
        var tv_challenge:TextView?=null
        var tv_against:TextView?=null
        var iv_battle:ImageView?=null
    }
}