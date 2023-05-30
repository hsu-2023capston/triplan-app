package com.capstone.triplan.di

import com.capstone.triplan.R
import java.text.SimpleDateFormat

object CommonUtil {
    fun getTime(timeStamp: Long) : String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(timeStamp)
    }

    fun setProfileImage(pid: Int): Int {
        when(pid){
            0-> return R.drawable.bg_profile_00
            1-> return R.drawable.bg_profile_01
            2-> return R.drawable.bg_profile_02
            3-> return R.drawable.bg_profile_03
            4-> return R.drawable.bg_profile_04
        }
        return R.drawable.bg_profile_00
    }
}