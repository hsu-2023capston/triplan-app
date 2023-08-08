package com.capstone.triplan.ui.fragment

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import com.capstone.triplan.*
import com.capstone.triplan.databinding.FragmentTripScheduleBinding
import com.prolificinteractive.materialcalendarview.CalendarDay

class TripScheduleFragment : BaseFragment<FragmentTripScheduleBinding>(R.layout.fragment_trip_schedule) {
    @SuppressLint("ResourceType")
    override fun initView() {
        binding.apply {
            tbTripSchedule.tbTrip.setNavigationOnClickListener{
                findNavController().navigateUp()
            }
            cvTripSchedule.apply {
                selectedDate = CalendarDay.today()
                addDecorators(SundayDecorator(), SaturdayDecorator(),OneDayDecorator())
                setHeaderTextAppearance(R.style.cv_month)
                //selectionMode = SELECTION_MODE_NONE
            }
        }
    }
}