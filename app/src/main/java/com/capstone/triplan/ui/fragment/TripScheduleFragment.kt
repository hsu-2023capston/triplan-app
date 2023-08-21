package com.capstone.triplan.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.*
import com.capstone.triplan.databinding.FragmentTripScheduleBinding
import com.capstone.triplan.presentation.adapter.TimeTableAdapter
import com.capstone.triplan.presentation.viewModel.TripScheduleViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@AndroidEntryPoint
class TripScheduleFragment : BaseFragment<FragmentTripScheduleBinding>(R.layout.fragment_trip_schedule) {
    private val viewModel: TripScheduleViewModel by viewModels()
    private val timeTableAdapter = TimeTableAdapter()
    private var index = 0

    override fun initView() {
        binding.apply {
            viewModel.trip.observe(viewLifecycleOwner) { trip ->
                val endDate =
                    LocalDate.parse(trip.end_date, DateTimeFormatter.ofPattern("yyyy.MM.dd."))
                val now = LocalDate.now()
                if (now.isAfter(endDate)) {
                    context?.let { tbTripSchedule.cvTripHome.setCardBackgroundColor(it.getColor(R.color.gray_blur)) }
                }
                tbTripSchedule.apply {
                    tbTrip.apply {
                        setNavigationOnClickListener { findNavController().navigateUp() }
                        setOnMenuItemClickListener {
                            when (it.itemId) {
                                R.id.edit_trip -> {
                                    Toast.makeText(
                                        this@TripScheduleFragment.context,
                                        "수정",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    true
                                }

                                R.id.delete_trip -> {
                                    Toast.makeText(
                                        this@TripScheduleFragment.context,
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
                cvTripSchedule.apply {
                    selectedDate = CalendarDay.today()
                    addDecorators(SundayDecorator(), SaturdayDecorator(), OneDayDecorator())
                    setHeaderTextAppearance(R.style.cv_month)
                    //selectionMode = SELECTION_MODE_NONE
                }
                rvTripSchedule.adapter = timeTableAdapter
                viewModel.timeTableDate.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty())
                        tvTripScheduleDate.text = it[index]
                }


                tvTripScheduleDayCount.text =
                    String.format(resources.getString(R.string.tripDayCount), index + 1)
                ibTripScheduleNextDay.setOnClickListener {
                    if (viewModel.timeTableDate.value != null)
                        if (index < viewModel.timeTableDate.value?.size?.minus(1)!!) {
                            index += 1
                            tvTripScheduleDate.text = viewModel.timeTableDate.value?.get(index)
                            viewModel.timeTable.value?.get(index)?.second?.let {
                                timeTableAdapter.setData(
                                    it
                                )
                            }
                            tvTripScheduleDayCount.text =
                                String.format(resources.getString(R.string.tripDayCount), index + 1)
                            context?.let { it1 -> ibTripSchedulePrevDay.setColorFilter(it1.getColor(R.color.blue_200)) }
                            if (index == viewModel.timeTableDate.value?.size?.minus(1))
                                context?.let { it1 -> ibTripScheduleNextDay.setColorFilter(it1.getColor(R.color.gray)) }
                        }
                }
                ibTripSchedulePrevDay.setOnClickListener {
                    if (viewModel.timeTableDate.value != null)
                        if (index > 0) {
                            index -= 1
                            tvTripScheduleDate.text = viewModel.timeTableDate.value?.get(index)
                            viewModel.timeTable.value?.get(index)?.second?.let {
                                timeTableAdapter.setData(
                                    it
                                )
                            }
                            tvTripScheduleDayCount.text =
                                String.format(resources.getString(R.string.tripDayCount), index + 1)
                            context?.let { it1 -> ibTripScheduleNextDay.setColorFilter(it1.getColor(R.color.blue_200)) }
                            if (index == 0)
                                context?.let { it1 -> ibTripSchedulePrevDay.setColorFilter(it1.getColor(R.color.gray)) }
                        }
                }
            }
            viewModel.timeTable.observe(viewLifecycleOwner) {
                if (it != null) {
                    if (it.isNotEmpty())
                        timeTableAdapter.setData(it[index].second)
                    else {
                        Toast.makeText(
                            this@TripScheduleFragment.context,
                            "일정이 없습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}