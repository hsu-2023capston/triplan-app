package com.myosa.triplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myosa.triplan.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}