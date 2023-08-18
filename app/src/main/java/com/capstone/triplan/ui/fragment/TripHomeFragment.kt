package com.capstone.triplan.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.capstone.domain.model.DomainTimeTable
import com.capstone.triplan.BaseFragment
import com.capstone.triplan.R
import com.capstone.triplan.databinding.FragmentTripHomeBinding
import com.capstone.triplan.presentation.adapter.TimeTableAdapter
import com.capstone.triplan.presentation.adapter.TripUserAdapter
import com.capstone.triplan.presentation.viewModel.TripHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class TripHomeFragment : BaseFragment<FragmentTripHomeBinding>(R.layout.fragment_trip_home) {
    private val viewModel: TripHomeViewModel by viewModels()
    private val tripUserAdapter = TripUserAdapter()
    private val timeTableAdapter = TimeTableAdapter()
    private var index = 0
    private val size by lazy {  }

    @SuppressLint("ResourceAsColor")
    override fun initView() {
        binding.apply {
            viewModel.trip.observe(viewLifecycleOwner) { trip ->
                val endDate =
                    LocalDate.parse(trip.end_date, DateTimeFormatter.ofPattern("yyyy.MM.dd."))
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
                                R.id.edit_trip -> {
                                    Toast.makeText(
                                        this@TripHomeFragment.context,
                                        "수정",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    true
                                }

                                R.id.delete_trip -> {
                                    Toast.makeText(
                                        this@TripHomeFragment.context,
                                        "삭제",
                                        Toast.LENGTH_SHORT
                                    ).show()
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
            viewModel.tripUser.observe(viewLifecycleOwner) {
                tripUserAdapter.setData(it)
            }
            viewModel.timeTableDate.observe(viewLifecycleOwner){
                tvTripHomeDate.text = it[index]
            }
            viewModel.timeTable.observe(viewLifecycleOwner){
                it?.get(index)?.second?.let { it1 -> timeTableAdapter.setData(it1) }
            }
            tvTripHomeDayCount.text = String.format(resources.getString(R.string.tripDayCount), index + 1)
            ibTripHomeNextDay.setOnClickListener {
                if (index < viewModel.timeTableDate.value?.size?.minus(1)!!) {
                    index += 1
                    tvTripHomeDate.text = viewModel.timeTableDate.value?.get(index)
                    viewModel.timeTable.value?.get(index)?.second?.let { timeTableAdapter.setData(it) }
                    tvTripHomeDayCount.text =
                        String.format(resources.getString(R.string.tripDayCount), index + 1)
                    ibTripHomePrevDay.setColorFilter(Color.parseColor("#FF087FCB"))
                    if (index == viewModel.timeTableDate.value?.size?.minus(1))
                        ibTripHomeNextDay.setColorFilter(Color.parseColor("#FF8F8F8F"))
                }
            }
            ibTripHomePrevDay.setOnClickListener {
                if (index > 0) {
                    index -= 1
                    tvTripHomeDate.text = viewModel.timeTableDate.value?.get(index)
                    viewModel.timeTable.value?.get(index)?.second?.let { timeTableAdapter.setData(it) }
                    tvTripHomeDayCount.text =
                        String.format(resources.getString(R.string.tripDayCount), index + 1)
                    ibTripHomeNextDay.setColorFilter(Color.parseColor("#FF087FCB"))
                    if (index == 0)
                        ibTripHomePrevDay.setColorFilter(Color.parseColor("#FF8F8F8F"))
                }
            }

            rvTripUser.adapter = tripUserAdapter
            rvTripHomeSchedule.adapter = timeTableAdapter
        }

    }


}
