package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.BottomNavigationArgs
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentTripHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripHomeFragment : BaseFragment<FragmentTripHomeBinding>(R.layout.fragment_trip_home) {
    private val args by navArgs<BottomNavigationArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        binding.apply {
            tbTripHome.apply {
                tbTrip.apply {
                    setNavigationOnClickListener {
                        findNavController().navigateUp()
                    }
                    setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.edit_trip -> { Toast.makeText(this@TripHomeFragment.context, "수정", Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.delete_trip -> { Toast.makeText(this@TripHomeFragment.context, "삭제", Toast.LENGTH_SHORT).show()
                                true
                            }
                            else -> false
                        }
                    }
                }
                tvTripTitle.text = args.trip.trip_name
                tvTripStartDate.text = args.trip.start_date
                //args.trip.start_date.
                //tvTripEndDate.text = args.trip.end_date
            }
            Glide.with(ivTripHome)
                .load("http://210.119.104.148:12345${args.trip.trip_path}")
                .into(ivTripHome)
        }

    }


}