package com.capstone.triplan.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentTripHomeBinding
import com.capstone.triplan.presentation.adapter.TripUserAdapter
import com.capstone.triplan.presentation.viewModel.TripHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class TripHomeFragment : BaseFragment<FragmentTripHomeBinding>(R.layout.fragment_trip_home) {
    private val viewModel: TripHomeViewModel by viewModels()
    private val tripUserAdapter = TripUserAdapter()
    override fun initView() {

        binding.apply {
            viewModel.trip.observe(viewLifecycleOwner) { trip ->
                val endDate = LocalDate.parse(trip.end_date, DateTimeFormatter.ofPattern("yyyy.MM.dd."))
                val now = LocalDate.now()
                if (now.isAfter(endDate)) {
                    context?.let { tbTripHome.cvTripHome.setCardBackgroundColor(it.getColor(R.color.gray_blur)) }
                }
                viewModel.getTripUser(trip.trip_id)
                Toast.makeText(this@TripHomeFragment.context, "${trip}", Toast.LENGTH_SHORT).show()
                tbTripHome.apply {
                    tbTrip.apply {
                        setNavigationOnClickListener { findNavController().navigateUp() }
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
                    tvTripTitle.text = trip.trip_name
                    tvTripStartDate.text = trip.start_date
                    tvTripEndDate.text = trip.end_date

                }
                Glide.with(ivTripHome)
                    .load("http://210.119.104.148:12345${trip.trip_path}")
                    .into(ivTripHome)
            }
            viewModel.tripUser.observe(viewLifecycleOwner){
                tripUserAdapter.setData(it)
            }
            rvTripUser.adapter = tripUserAdapter


        }

    }



}