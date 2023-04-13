package com.myosa.triplan.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.myosa.triplan.BaseActivity
import com.myosa.triplan.R
import com.myosa.triplan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController

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
        }
    }



}