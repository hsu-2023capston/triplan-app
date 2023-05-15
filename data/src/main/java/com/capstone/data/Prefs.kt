package com.capstone.data

import android.content.SharedPreferences

class Prefs (private  val prefs: SharedPreferences) {
    var jwt:String
        get() = prefs.getString("JWT_TOKEN", "NO_TOKEN")?:"NO_TOKEN"
        set(jwt:String){
            prefs.edit().putString("JWT_TOKEN",jwt).apply()
        }

    var user_id:Int
        get() = prefs.getInt("USER_ID",-1)
        set(user_id:Int){
            prefs.edit().putInt("USER_ID",user_id).apply()
        }
}