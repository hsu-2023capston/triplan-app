package com.capstone.triplan.di

import java.text.SimpleDateFormat

object CommonUtil {
    fun getTime(timeStamp: Long) : String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(timeStamp)
    }
}