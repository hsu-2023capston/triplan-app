package com.myosa.triplan.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.myosa.triplan.BaseActivity
import com.myosa.triplan.R
import com.myosa.triplan.databinding.ActivityMainBinding
import com.myosa.triplan.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController
    private val  mainModel : MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            val navHomeFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHomeFragment.navController

            val tripFragments = arrayOf(
                R.id.tripArchiveFragment,
                R.id.tripHomeFragment,
                R.id.tripScheduleFragment,
                R.id.tripChatFragment)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id in tripFragments ) {
                    binding.bnMenu.visibility = View.VISIBLE
                    setupWithNavController(binding.bnMenu, navController)
                } else {
                    binding.bnMenu.visibility = View.GONE
                }
            }

            mainModel.isLogin.observe(this@MainActivity){
                if(it){
                    loge("로그인 되어있음")
                }
            }
        }
    }



}