package com.capstone.data

import android.content.SharedPreferences
import com.capstone.domain.model.DomainTrip

class Prefs (private  val prefs: SharedPreferences) {
    var user_id:Int
        get() = prefs.getInt("USER_ID",-1)
        set(user_id:Int){
            prefs.edit().putInt("USER_ID",user_id).apply()
        }

     var trip: String
         get() = prefs.getString("TRIP","비어있음").toString()
         set(trip:String){
             prefs.edit().putString("TRIP",trip).apply()
         }


}