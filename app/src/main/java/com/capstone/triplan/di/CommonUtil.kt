package com.capstone.triplan.di

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager
import com.capstone.triplan.R
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL
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

    fun isURL(text: String): Boolean {
        return try {
            val url = URL(text)
            url.toURI() // This line checks if the URL is valid
            true
        } catch (e: MalformedURLException) {
            false
        } catch (e: URISyntaxException) {
            false
        }
    }

    fun createHyperlink(url: String): String {
        return "<a href=\"$url\">$url</a>"
    }

    fun getScreenWidth(activity: Activity): Int {
        val windowManager = activity.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        }else{
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

}