package com.huim_lin.trust.bean

/**
 * user
 * Created by huim_lin on 2017/9/19.
 */
class User {
    object BattleState{
        val N_Battle_normal=0
        val N_Battle_challenge=1
        val N_Battle_against=2
    }

    var name:String?=null
    var alias:String?=null
    var score:String?=null
    var challenge:String?=null
    var against:String?=null
    var battle:Int?=null

}