package com.myosa.triplan.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.myosa.triplan.BaseFragment
import com.myosa.triplan.R
import com.myosa.triplan.databinding.FragmentTripHomeBinding


class TripHomeFragment :BaseFragment<FragmentTripHomeBinding>(R.layout.fragment_trip_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        binding.apply {
            includedToolbar.tbTrip.setNavigationOnClickListener{
                findNavController().popBackStack()
            }
            includedToolbar.tbTrip.setOnMenuItemClickListener{when(it.itemId){
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