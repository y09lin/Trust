package com.huim_lin.trust.bean

import java.io.Serializable

/**
 * user
 * Created by huim_lin on 2017/9/19.
 */
class User :Serializable{
    object BattleState{
        val N_Battle_normal=0
        val N_Battle_challenge=1
        val N_Battle_against=2
    }

    var id:String?=null
    var name:String?=null
    var alias:String?=null
    var score:String?=null
    var challenge:String?=null
    var against:String?=null
    var battle:Int?=null

    companion object {
        private const val serialVersionUID = 1L
    }
}