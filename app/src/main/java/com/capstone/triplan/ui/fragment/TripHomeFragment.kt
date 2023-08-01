package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentTripHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripHomeFragment :BaseFragment<FragmentTripHomeBinding>(R.layout.fragment_trip_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        binding.apply {
            tbTripHome.tbTrip.setNavigationOnClickListener{
                findNavController().navigateUp()
            }
            tbTripHome.tbTrip.setOnMenuItemClickListener{when(it.itemId){
                R.id.edit_trip -> {
                    Toast.makeText(this@TripHomeFragment.context, "수정", Toast.LENGTH_SHORT).show()
                    true}
                R.id.delete_trip -> {
                    Toast.makeText(this@TripHomeFragment.context, "삭제", Toast.LENGTH_SHORT).show()
                    true}
                else -> false
            }
            }



        }

    }


}