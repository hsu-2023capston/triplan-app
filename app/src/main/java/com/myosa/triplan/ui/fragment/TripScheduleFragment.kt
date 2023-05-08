package com.myosa.triplan.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myosa.triplan.*
import com.myosa.triplan.databinding.FragmentTripScheduleBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_NONE
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.SelectionMode

class TripScheduleFragment : BaseFragment<FragmentTripScheduleBinding>(R.layout.fragment_trip_schedule) {
    @SuppressLint("ResourceType")
    override fun initView() {
        binding.apply {
            tbTripSchedule.tbTrip.setNavigationOnClickListener{
                findNavController().popBackStack(R.layout.fragment_trip_home,false)
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