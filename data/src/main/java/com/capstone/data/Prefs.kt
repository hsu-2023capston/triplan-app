package com.capstone.data

import android.content.SharedPreferences

class Prefs (private  val prefs: SharedPreferences) {
    var user_id:Int
        get() = prefs.getInt("USER_ID",-1)
        set(user_id:Int){
            prefs.edit().putInt("USER_ID",user_id).apply()
        }


}